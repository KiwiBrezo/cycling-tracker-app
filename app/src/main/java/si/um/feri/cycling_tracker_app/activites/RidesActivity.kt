package si.um.feri.cycling_tracker_app.activites

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.adapters.RideCardAdapter
import si.um.feri.cycling_tracker_app.adapters.holder.RideCardData
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.utils.AppDatabase
import si.um.feri.cycling_tracker_app.utils.DateTimeUtils

class RidesActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    private lateinit var allRides: MutableList<RideData>
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rides)

        this.appDatabase = AppDatabase.getInstance(this)

        allRides = appDatabase.rideDataDao().getAllRideData()

        if (allRides.size == 0) {
            Toast.makeText(this, R.string.no_rides, Toast.LENGTH_SHORT).show()
        }

        this.recyclerview = findViewById(R.id.recyclerview)
        this.recyclerview.layoutManager = LinearLayoutManager(this)

        val rideDataHolders = mutableListOf<RideCardData>()
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

            override fun onLongClick(position: Int, model: RideCardData) {
                val checkIfSureDialog: AlertDialog? = this@RidesActivity.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setMessage(R.string.delete_ride_check)
                        setPositiveButton(R.string.approve,
                            DialogInterface.OnClickListener { dialog, id ->
                                val ride = appDatabase.rideDataDao().getRideById(model.rideId)
                                appDatabase.rideDataDao().delete(ride)
                                rideDataHolders.removeAt(position)
                                rideCardAdapter.notifyDataSetChanged()
                            })
                        setNegativeButton(R.string.not_approve,
                            DialogInterface.OnClickListener { dialog, id ->
                                // Nothing
                            })
                    }
                    builder.create()
                }

                checkIfSureDialog!!.show()
            }
        })

        this.recyclerview.adapter = rideCardAdapter
    }
}