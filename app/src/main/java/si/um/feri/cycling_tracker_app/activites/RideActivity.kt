package si.um.feri.cycling_tracker_app.activites

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.services.RideManagerService
import java.util.concurrent.TimeUnit


class RideActivity : AppCompatActivity() {

    private lateinit var map : MapView
    private lateinit var timer : MaterialTextView
    private lateinit var startPauseBtn : Button
    private lateinit var stopBtn: Button
    private lateinit var settingsBtn : ImageView
    private lateinit var locationManager: LocationManager

    private lateinit var rideLocationManager: RideManagerService

    private lateinit var locationMarker : Drawable

    private var currentLocation: Location? = null

    private val mInterval = 1000 // 1 second in this case
    private var mHandler: Handler? = null
    private var timeInSeconds = 0L
    private var startButtonClicked = false
    private var hasGps = false
    private var rideHasStarted = false

    private val gpsLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = location
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_ride)

        rideLocationManager = RideManagerService.getInstance(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                225
            )
        }

        this.map = findViewById(R.id.mapview_new)
        this.startPauseBtn = findViewById(R.id.start_pause_btn)
        this.stopBtn = findViewById(R.id.stop_btn)
        this.settingsBtn = findViewById(R.id.settings_btn_ride)
        this.timer = findViewById(R.id.time_counter_textview)

        this.locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.hasGps = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (hasGps) {
            this.locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                900,
                0F,
                this.gpsLocationListener
            )
        }

        timer.text = "00:00:00"
        stopBtn.visibility = View.GONE;

        val dr = getDrawable(R.drawable.red_circle_location)
        val bitmap: Bitmap = (dr as BitmapDrawable).bitmap
        locationMarker = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 35, 24, true));

        rideHasStarted = false

        bindAndSetUpMap()
        bindButtons()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    @SuppressLint("MissingPermission")
    private fun bindAndSetUpMap() {
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true);
        val mapController = map.controller
        mapController.setZoom(19)
        val userGeoLocation = GeoPoint(this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!.latitude,
                                       this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!.longitude)
        if (userGeoLocation != null) {
            val userMarker = Marker(this.map)
            userMarker.position = userGeoLocation
            userMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            map.overlays.add(userMarker)
            map.controller.animateTo(userGeoLocation)
        }
    }

    private fun bindButtons() {
        startPauseBtn.setOnClickListener {
            startOrStopButtonClicked()
        }

        stopBtn.setOnClickListener {
            stopAndResetTimerView()
        }
    }

    private fun startOrStopButtonClicked() {
        stopBtn.visibility = View.VISIBLE;

        if (!startButtonClicked) {
            startTimer()
            startTimerView()
        } else {
            stopTimer()
            pauseTimerView()
        }
    }

    private fun startTimer() {
        mHandler = Handler(Looper.getMainLooper())
        timerStatusChecker.run()
        if (!rideHasStarted) {
            // TODO
            rideLocationManager.startRideLocation()
            rideHasStarted = true
        }
    }

    private fun stopTimer() {
        mHandler?.removeCallbacks(timerStatusChecker)
    }

    private fun startTimerView() {
        startPauseBtn.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.secondary_dark
            )
        )
        startPauseBtn.setText(R.string.pause_btn_text)
        startButtonClicked = true
    }

    private fun pauseTimerView() {
        startPauseBtn.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.secondary_normal
            )
        )
        startPauseBtn.setText(R.string.start_btn_text)
        startButtonClicked = false
    }

    private var timerStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                timeInSeconds += 1
                updateStopWatchView(timeInSeconds)
                saveAndShowCurrentLocation()
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler!!.postDelayed(this, mInterval.toLong())
            }
        }
    }

    private fun updateStopWatchView(timeInSeconds: Long) {
        val formattedTime = getFormattedStopWatch((timeInSeconds * 1000))
        timer.text = formattedTime
    }

    fun getFormattedStopWatch(ms: Long): String {
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return "${if (hours < 10) "0" else ""}$hours:" +
                "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }

    private fun stopAndResetTimerView() {
        // TODO
        rideLocationManager.stopRideLocation()
        rideHasStarted = false

        stopTimer()

        timeInSeconds = 0
        startButtonClicked = false
        startPauseBtn.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.secondary_normal
            )
        )
        startPauseBtn.setText(R.string.start_btn_text)
        timer.text = "00:00:00"
        stopBtn.visibility = View.GONE;

        map.overlays.forEach {
            if (it is Marker) {
                map.overlays.remove(it)
            }
        }

        Toast.makeText(this, "Ride saved to local database!", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun saveAndShowCurrentLocation() {
        if (!hasGps) {
            return
        }

        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (currentLocation != null) {
            val startMarker = Marker(map)
            val geoLocation = GeoPoint(currentLocation!!.latitude, currentLocation!!.longitude)
            startMarker.position = geoLocation
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            startMarker.icon = locationMarker
            map.overlays.add(startMarker)
            map.controller.animateTo(geoLocation)

            // TODO
            rideLocationManager.saveRideLocation()
        }
    }
}