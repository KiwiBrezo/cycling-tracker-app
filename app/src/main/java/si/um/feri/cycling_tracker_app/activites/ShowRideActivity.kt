package si.um.feri.cycling_tracker_app.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.utils.AppDatabase

class ShowRideActivity : AppCompatActivity() {

    private lateinit var map : MapView
    private lateinit var appDatabase: AppDatabase

    private lateinit var thisRide: RideData

    var startPoint = GeoPoint(48.8583, 2.2944)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_show_ride)

        appDatabase = AppDatabase.getInstance(this)

        map = findViewById<View>(R.id.mapview_history) as MapView

        if(intent.hasExtra("ride_id")){
            // get the Serializable data model class with the details in it
            val rideId = intent.getIntExtra("ride_id", -1)
            this.thisRide = appDatabase.rideDataDao().getRideById(rideId)
            this.startPoint = GeoPoint(this.thisRide.rideLine[0][0], this.thisRide.rideLine[0][1])
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
        // TODO add function to show points of ride on map
    }
}