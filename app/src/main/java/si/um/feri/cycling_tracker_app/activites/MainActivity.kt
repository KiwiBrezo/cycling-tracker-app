package si.um.feri.cycling_tracker_app.activites

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.services.RideManagerService


class MainActivity : AppCompatActivity() {

    private lateinit var startBtn : Button
    private lateinit var historyBtn : Button
    private lateinit var settingsBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.startBtn = findViewById(R.id.start_btn)
        this.historyBtn = findViewById(R.id.history_btn)
        this.settingsBtn = findViewById(R.id.settings_btn_main)

        bindComponents()

        val sharedPref = this.getSharedPreferences("cycling-tracker-app-USER", Context.MODE_PRIVATE)
        val userToken : String? = sharedPref.getString("user-token", "")

        if (userToken!!.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            checkForPermissions()

            var rideLocationManager = RideManagerService.getInstance(this)
            rideLocationManager.checkForNotEndedRide()
        }
    }

    private fun bindComponents() {
        this.startBtn.setOnClickListener {
            val intent = Intent(this, RideActivity::class.java)
            startActivity(intent)
        }

        this.historyBtn.setOnClickListener {
            val intent = Intent(this, RidesActivity::class.java)
            startActivity(intent)
        }

        this.settingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    // TODO need to improve that (https://stackoverflow.com/questions/35484767/activitycompat-requestpermissions-not-showing-dialog-box)
    private fun checkForPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_NETWORK_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                ),
                225
            )
        }
    }
}