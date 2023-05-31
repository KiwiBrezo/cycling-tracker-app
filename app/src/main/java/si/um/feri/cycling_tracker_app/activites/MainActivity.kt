package si.um.feri.cycling_tracker_app.activites

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import si.um.feri.cycling_tracker_app.R


class MainActivity : AppCompatActivity() {

    private lateinit var startBtn : Button
    private lateinit var historyBtn : Button
    private lateinit var settingsBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_NETWORK_STATE
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                225
            )
        }

        this.startBtn = findViewById(R.id.start_btn)
        this.historyBtn = findViewById(R.id.history_btn)
        this.settingsBtn = findViewById(R.id.settings_btn_main)

        bindComponents()

        val sharedPref = this.getSharedPreferences("cycling-tracker-app-USER", Context.MODE_PRIVATE)
        val userToken : String? = sharedPref.getString("user-token", "")

        if (userToken!!.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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