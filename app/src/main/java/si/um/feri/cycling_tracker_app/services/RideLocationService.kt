package si.um.feri.cycling_tracker_app.services

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import org.greenrobot.eventbus.EventBus
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.models.events.LocationEvent

// https://github.com/codepath/android_guides/issues/220
class RideLocationService : Service() {

    private val TAG = "RideLocationService"
    private var locationManager: LocationManager? = null

    private val LOCATION_INTERVAL = 500
    private val LOCATION_DISTANCE = 5f

    var locationListeners = arrayOf(
        LocationListener(LocationManager.PASSIVE_PROVIDER)
    )

    class LocationListener(provider: String) : android.location.LocationListener {
        private val TAG = "RideLocationService.LocationListener"

        var mLastLocation: Location

        init {
            Log.i(TAG, "LocationListener $provider")
            mLastLocation = Location(provider)
        }

        override fun onLocationChanged(location: Location) {
            mLastLocation.set(location)
            Log.i(TAG, "Last location [${mLastLocation.latitude}, ${mLastLocation.longitude}]")
            EventBus.getDefault().post(LocationEvent(latitude = mLastLocation.latitude, longitude = mLastLocation.longitude, location = mLastLocation))
        }

        override fun onProviderDisabled(provider: String) {
        }

        override fun onProviderEnabled(provider: String) {
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        }
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onCreate() {
        Log.i(TAG, "initializeLocationManager - LOCATION_INTERVAL: $LOCATION_INTERVAL LOCATION_DISTANCE: $LOCATION_DISTANCE")
        if (locationManager == null) {
            locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        try {
            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                LOCATION_INTERVAL.toLong(),
                LOCATION_DISTANCE,
                locationListeners[0]
            )
        } catch (ex: SecurityException) {
            Log.e(TAG, "fail to request location update, ignore", ex)
            Toast.makeText(this, R.string.error_in_app, Toast.LENGTH_SHORT).show()
        } catch (ex: IllegalArgumentException) {
            Log.e(TAG, "network provider does not exist, " + ex.message)
            Toast.makeText(this, R.string.error_in_app, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locationManager != null) {
            for (i in locationListeners.indices) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    locationManager!!.removeUpdates(locationListeners[i])
                } catch (ex: Exception) {
                    Log.e(TAG, "fail to remove location listener, ignore", ex)
                    Toast.makeText(this, R.string.error_in_app, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}