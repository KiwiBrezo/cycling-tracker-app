package si.um.feri.cycling_tracker_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ride")
data class RideData(
    @PrimaryKey(autoGenerate = true)
    val ride_id: Int = 0,
    var timeStart: Long,
    var timeStop: Long,
    var duration: Long,
    var user_id: Int,
    var rideLine: MutableList<List<Double>>,
    var is_active: Int,
    var is_uploaded: Int
) : Serializable