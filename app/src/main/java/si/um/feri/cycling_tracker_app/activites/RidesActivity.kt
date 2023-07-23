package si.um.feri.cycling_tracker_app.activites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.adapters.RideCardAdapter
import si.um.feri.cycling_tracker_app.adapters.holder.RideCardData
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.utils.AppDatabase
import si.um.feri.cycling_tracker_app.utils.DateTimeUtils
import java.text.SimpleDateFormat
import java.util.Calendar

class RidesActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    private lateinit var allRides: MutableList<RideData>
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rides)

        this.appDatabase = AppDatabase.getInstance(this)

        allRides = appDatabase.rideDataDao().getAllRideData()

        this.recyclerview = findViewById(R.id.recyclerview)
        this.recyclerview.layoutManager = LinearLayoutManager(this)

        val rideDataHolders = ArrayList<RideCardData>()
        for (i in 1..allRides.size) {
            rideDataHolders.add(RideCardData(
                allRides[i - 1].ride_id,
                "Voznja ${i}",
                DateTimeUtils.getDate(allRides[i - 1].timeStart, "dd.MM.yyyy")!!,
                DateTimeUtils.getFormattedStopWatch(allRides[i - 1].duration * 1000),
            ))
        }

        var rideCardAdapter = RideCardAdapter(rideDataHolders)
        rideCardAdapter.setOnClickListener(object : RideCardAdapter.OnClickListener {
            override fun onClick(position: Int, model: RideCardData) {
                val intent = Intent(this@RidesActivity, ShowRideActivity::class.java)
                intent.putExtra("ride_id", model.rideId)
                startActivity(intent)
            }
        })

        this.recyclerview.adapter = rideCardAdapter
    }
}