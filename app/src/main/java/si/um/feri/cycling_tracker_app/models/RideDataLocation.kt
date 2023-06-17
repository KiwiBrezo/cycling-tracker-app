package si.um.feri.cycling_tracker_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ride_location")
data class RideDataLocation(
    @PrimaryKey(autoGenerate = true)
    val location_id: Int = 0,

    val timestamp: Long,

    val ride_id: Int,

    val longitude: Double,

    val latitude: Double,

    val is_uploaded: Int
) : Serializable