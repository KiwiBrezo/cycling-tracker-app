package si.um.feri.cycling_tracker_app.activites

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import si.um.feri.cycling_tracker_app.AppController
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.models.UserData
import si.um.feri.cycling_tracker_app.models.events.LocationEvent
import si.um.feri.cycling_tracker_app.models.events.LocationUploadEvent
import si.um.feri.cycling_tracker_app.models.events.RideUploadEvent
import si.um.feri.cycling_tracker_app.utils.RideManager
import si.um.feri.cycling_tracker_app.utils.AppDatabase
import si.um.feri.cycling_tracker_app.utils.DateTimeUtils


class RideActivity : AppCompatActivity() {
    private val appController = AppController.applicationContext()

    private lateinit var map : MapView
    private lateinit var timer : MaterialTextView
    private lateinit var startPauseBtn : Button
    private lateinit var stopBtn: Button
    private lateinit var settingsBtn : ImageView

    private lateinit var rideManager: RideManager
    private lateinit var appDatabase: AppDatabase

    private lateinit var locationMarker : Drawable

    private var currentLocation: Location? = null

    private val interval = 1000 // 1 second in this case
    private var rideTrackerHandler: Handler? = null
    private var timeInSeconds = 0L
    private var startButtonClicked = false
    private var hasGps = false
    private var rideHasStarted = false

    private var rideData: RideData? = null
    private var userData: UserData? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_ride)

        this.appController.askForPermission(this)
        this.appController.startServices()

        this.rideManager = RideManager.getInstance(this)
        this.appDatabase = AppDatabase.getInstance(this)
        val sharedPref = this.getSharedPreferences("cycling-tracker-app-USER", Context.MODE_PRIVATE)
        val userToken = sharedPref.getString("user-token", "")

        this.userData = this.appDatabase.userDataDao().getUserDataByToken(userToken!!)

        this.map = findViewById(R.id.mapview_new)
        this.startPauseBtn = findViewById(R.id.start_pause_btn)
        this.stopBtn = findViewById(R.id.stop_btn)
        this.settingsBtn = findViewById(R.id.settings_btn_ride)
        this.timer = findViewById(R.id.time_counter_textview)

        this.hasGps = true

        timer.text = "00:00:00"
        stopBtn.visibility = View.GONE;

        val dr = getDrawable(R.drawable.red_circle_location)
        val bitmap: Bitmap = (dr as BitmapDrawable).bitmap
        locationMarker = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 35, 24, true));

        rideHasStarted = false

        EventBus.getDefault().register(this)

        bindAndSetUpMap()
        bindButtons()

        Toast.makeText(this, R.string.loading_location, Toast.LENGTH_SHORT).show()
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
        if (rideHasStarted) {
            stopAndResetTimerView()
        }
        stopTimer()
        EventBus.getDefault().unregister(this)
    }

    @SuppressLint("MissingPermission")
    private fun bindAndSetUpMap() {
        if (!hasGps) {
            return
        }

        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true);
        val mapController = map.controller
        mapController.setZoom(19)
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
        rideTrackerHandler = Handler(Looper.getMainLooper())
        timerStatusChecker.run()
        if (!rideHasStarted) {
            // TODO
            this.rideData = this.rideManager.startRideLocation(userData!!.user_id, System.currentTimeMillis())
            this.rideHasStarted = true
        }
    }

    private fun stopTimer() {
        rideTrackerHandler?.removeCallbacks(timerStatusChecker)
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
                rideTrackerHandler!!.postDelayed(this, interval.toLong())
            }
        }
    }

    private fun updateStopWatchView(timeInSeconds: Long) {
        val formattedTime = DateTimeUtils.getFormattedStopWatch(timeInSeconds * 1000)
        timer.text = formattedTime
    }

    private fun stopAndResetTimerView() {
        this.rideData = rideManager.stopRideLocation(this.rideData!!.ride_id, System.currentTimeMillis(), timeInSeconds)
        rideHasStarted = false

        EventBus.getDefault().post(RideUploadEvent(ride_id = this.rideData!!.ride_id, user_id = this.rideData!!.user_id, duration = this.rideData!!.duration, timeStart = this.rideData!!.timeStart, timeStop = this.rideData!!.timeStop))

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

        Toast.makeText(this, R.string.ride_saved, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun saveAndShowCurrentLocation() {
        if (!hasGps) {
            return
        }

        if (currentLocation != null) {
            val startMarker = Marker(map)
            val geoLocation = GeoPoint(currentLocation!!.latitude, currentLocation!!.longitude)
            startMarker.position = geoLocation
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            startMarker.icon = locationMarker
            map.overlays.add(startMarker)
            map.controller.animateTo(geoLocation)

            if (this.rideData != null) {
                val rideLocation = rideManager.saveRideLocation(this.rideData!!.ride_id, this.userData!!.user_id, currentLocation!!.latitude, currentLocation!!.longitude)
                EventBus.getDefault().post(LocationUploadEvent(ride_id = rideLocation.ride_id, timestamp = rideLocation.timestamp, longitude = rideLocation.longitude, latitude = rideLocation.latitude))
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun showLocationOfUser(location: Location) {
        if (!rideHasStarted) {
            map.overlays.forEach {
                if (it is Marker) {
                    map.overlays.remove(it)
                }
            }

            val userGeoLocation = GeoPoint(location.latitude, location.longitude)
            if (userGeoLocation != null) {
                val userMarker = Marker(map)
                userMarker.position = userGeoLocation
                userMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                map.overlays.add(userMarker)
                map.controller.animateTo(userGeoLocation)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getLocationEvent(gpsEvent: LocationEvent) {
        Log.e("testEvent", "Location from  [${gpsEvent.latitude}, ${gpsEvent.longitude}]")
        currentLocation = gpsEvent.location
        showLocationOfUser(gpsEvent.location)
    }
}