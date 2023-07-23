package si.um.feri.cycling_tracker_app.activites

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.utils.AppDatabase

class ShowRideActivity : AppCompatActivity() {

    private lateinit var map : MapView
    private lateinit var appDatabase: AppDatabase

    private lateinit var thisRide: RideData

    private lateinit var locationMarker : Drawable

    var startPoint = GeoPoint(48.8583, 2.2944)
    var endPoint = GeoPoint(48.8583, 2.2944)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_show_ride)

        appDatabase = AppDatabase.getInstance(this)

        var dr = getDrawable(R.drawable.red_circle_location)
        var bitmap: Bitmap = (dr as BitmapDrawable).bitmap
        locationMarker = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 35, 24, true));

        map = findViewById<View>(R.id.mapview_history) as MapView

        if(intent.hasExtra("ride_id")){
            val rideId = intent.getIntExtra("ride_id", -1)
            this.thisRide = appDatabase.rideDataDao().getRideById(rideId)

            if (this.thisRide.rideLine.size < 2) {
                this.startPoint = GeoPoint(this.thisRide.rideLine[0][0], this.thisRide.rideLine[0][1])

                val startMarker = Marker(this.map)
                startMarker.position = this.startPoint
                startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                map.overlays.add(startMarker)
            } else {
                this.startPoint = GeoPoint(this.thisRide.rideLine[0][0], this.thisRide.rideLine[0][1])
                this.endPoint = GeoPoint(this.thisRide.rideLine.last()[0], this.thisRide.rideLine.last()[1])

                val startMarker = Marker(this.map)
                startMarker.position = this.startPoint
                startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                map.overlays.add(startMarker)

                val endMarker = Marker(this.map)
                endMarker.position = this.endPoint
                endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                map.overlays.add(endMarker)
            }
        }

        bindAndSetUpMap()
        setupRideOnMap()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    private fun bindAndSetUpMap() {
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true);
        val mapController = map.controller
        mapController.setZoom(16)
        mapController.setCenter(this.startPoint)
    }

    private fun setupRideOnMap() {
        if (this.thisRide == null) {
            return
        }

        this.thisRide.rideLine.forEach {
            val startMarker = Marker(map)
            val geoLocation = GeoPoint(it[0], it[1])
            startMarker.position = geoLocation
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            startMarker.icon = locationMarker
            map.overlays.add(startMarker)
        }
    }
}