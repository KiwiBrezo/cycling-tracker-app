package si.um.feri.cycling_tracker_app.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import si.um.feri.cycling_tracker_app.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var clearDataBtn : Button
    private lateinit var logoutBtn : Button
    private lateinit var mainOrBackBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        this.clearDataBtn = findViewById(R.id.clear_data_btn)
        this.logoutBtn = findViewById(R.id.logout_btn)
        this.mainOrBackBtn = findViewById(R.id.main_activity_btn)

        bindComponents()
    }

    private fun bindComponents() {
        this.clearDataBtn.setOnClickListener {
            // TODO
        }

        this.logoutBtn.setOnClickListener {
            // TODO
        }

        this.mainOrBackBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}