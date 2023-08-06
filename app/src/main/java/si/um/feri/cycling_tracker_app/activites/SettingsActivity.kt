package si.um.feri.cycling_tracker_app.activites

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.utils.AppDatabase

class SettingsActivity : AppCompatActivity() {

    private lateinit var clearDataBtn : Button
    private lateinit var logoutBtn : Button
    private lateinit var mainOrBackBtn : ImageView

    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        this.clearDataBtn = findViewById(R.id.clear_data_btn)
        this.logoutBtn = findViewById(R.id.logout_btn)
        this.mainOrBackBtn = findViewById(R.id.main_activity_btn)

        this.appDatabase = AppDatabase.getInstance(this)

        bindComponents()
    }

    private fun bindComponents() {
        this.clearDataBtn.setOnClickListener {
            clearDataFromDatabase()
        }

        this.logoutBtn.setOnClickListener {
            logoutUser()
        }

        this.mainOrBackBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logoutUser() {
        val sharedPref = this.getSharedPreferences("cycling-tracker-app-USER", Context.MODE_PRIVATE)
        val userToken = sharedPref.getString("user-token", "")
        with (sharedPref.edit()) {
            putString("user-token", "")
            apply()
        }

        if (userToken!!.isNotEmpty()) {
            var userToLogout = this.appDatabase.userDataDao().getUserDataByToken(userToken!!)
            this.appDatabase.userDataDao().delete(userToLogout)
        }

        clearDataFromDatabase()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun clearDataFromDatabase() {
        var rideDatas = this.appDatabase.rideDataDao().getAllRideData()

        rideDatas.forEach {
            this.appDatabase.rideDataDao().delete(it)
        }

        var rideLocationDatas = this.appDatabase.rideDataLocationDao().getAllRideLocationData()
        rideLocationDatas.forEach {
            this.appDatabase.rideDataLocationDao().delete(it)
        }

        Toast.makeText(this, R.string.cleared_data, Toast.LENGTH_SHORT).show()
    }
}