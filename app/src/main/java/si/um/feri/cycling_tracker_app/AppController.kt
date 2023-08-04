package si.um.feri.cycling_tracker_app

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import si.um.feri.cycling_tracker_app.services.RideLocationService

class AppController : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AppController? = null

        fun applicationContext() : AppController {
            return instance as AppController
        }
    }

    private lateinit var locationService: Intent
    private lateinit var uploadService: Intent

    private var serviceStarted = false;
    private var accessFineLocation = false;
    private var accessCoarseLocation = false;
    private var accessBackgroundLocation = false;
    private var accessInternet = false;
    private var writeExternalStorage = false;
    private var accessNetworkState = false;

    override fun onCreate() {
        super.onCreate()
        checkForPermissions()
        startServices()
    }

    fun startServices() {
        if (!this.accessCoarseLocation || !this.accessFineLocation || !this.accessBackgroundLocation || this.serviceStarted) {
            return
        }
        this.locationService = Intent(this, RideLocationService::class.java)
        this.startService(this.locationService)

        this.uploadService = Intent(this, RideLocationService::class.java)
        this.startService(this.uploadService)

        this.serviceStarted = true;
    }

    // TODO need to improve that (https://stackoverflow.com/questions/35484767/activitycompat-requestpermissions-not-showing-dialog-box)
    private fun checkForPermissions() {
        this.accessFineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        this.accessCoarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        this.accessBackgroundLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
        this.accessInternet = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
        this.writeExternalStorage = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        this.accessNetworkState = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
    }

    fun askForPermission(activity: Activity) {
        if (!this.accessFineLocation) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                225
            )
        }

        if (!this.accessCoarseLocation) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
                225
            )
        }

        if (!this.accessBackgroundLocation) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                ),
                225
            )
        }

        /*if (!this.accessBackgroundLocation || !this.accessFineLocation || !this.accessCoarseLocation) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                ),
                225
            )
        }*/

        if (!this.accessInternet) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.INTERNET,
                ),
                225
            )
        }

        if (!this.writeExternalStorage) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                ),
                225
            )
        }

        if (!this.accessNetworkState) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_NETWORK_STATE,
                ),
                225
            )
        }
    }

    fun hasAllPermissions(): Boolean {
        checkForPermissions()
        return this.accessFineLocation &&
                this.accessCoarseLocation &&
                this.accessBackgroundLocation &&
                this.accessInternet &&
                this.accessNetworkState &&
                this.writeExternalStorage
    }
}