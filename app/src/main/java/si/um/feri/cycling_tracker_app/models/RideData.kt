package si.um.feri.cycling_tracker_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ride")
data class RideData(
    @PrimaryKey(autoGenerate = true)
    val ride_id: Int = 0,

    val timeStart: Long,

    val timeStop: Long,

    val user_id: Int,

    val rideLine: List<List<Double>>
) : Serializable