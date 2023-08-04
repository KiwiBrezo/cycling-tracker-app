package si.um.feri.cycling_tracker_app.activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import si.um.feri.cycling_tracker_app.AppController
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.utils.RideManager


class MainActivity : AppCompatActivity() {

    private val appController = AppController.applicationContext()

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

        appController.askForPermission(this)

        val sharedPref = this.getSharedPreferences("cycling-tracker-app-USER", Context.MODE_PRIVATE)
        val userToken : String? = sharedPref.getString("user-token", "")

        if (userToken!!.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            this.appController.startServices()
            var rideLocationManager = RideManager.getInstance(this)
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
}