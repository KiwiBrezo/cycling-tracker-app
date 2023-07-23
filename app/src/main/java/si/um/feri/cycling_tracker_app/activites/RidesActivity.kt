package si.um.feri.cycling_tracker_app.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.adapters.RideCardAdapter
import si.um.feri.cycling_tracker_app.adapters.holder.RideCardData
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.utils.AppDatabase
import java.util.ArrayList

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
                "Voznja ${i}",
                allRides[i - 1].timeStart.toString(),
                allRides[i - 1].duration.toString(),
            ))
        }

        this.recyclerview.adapter = RideCardAdapter(rideDataHolders)
    }
}