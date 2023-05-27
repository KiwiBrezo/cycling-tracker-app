package si.um.feri.cycling_tracker_app.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import si.um.feri.cycling_tracker_app.R

class MainActivity : AppCompatActivity() {

    private lateinit var startBtn : Button
    private lateinit var historyBtn : Button
    private lateinit var settingsBtn : ImageView

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(this, "You need this for the app to work!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.startBtn = findViewById(R.id.start_btn)
        this.historyBtn = findViewById(R.id.history_btn)
        this.settingsBtn = findViewById(R.id.settings_btn_main)

        bindComponents()

        /*when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            shouldShowRequestPermissionRationale(...) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected, and what
            // features are disabled if it's declined. In this UI, include a
            // "cancel" or "no thanks" button that lets the user continue
            // using your app without granting the permission.
            showInContextUI(...)
        }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.REQUESTED_PERMISSION)
            }
        }*/
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