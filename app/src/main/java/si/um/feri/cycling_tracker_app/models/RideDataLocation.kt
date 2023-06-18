package si.um.feri.cycling_tracker_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ride_location")
data class RideDataLocation(
    @PrimaryKey(autoGenerate = true)
    val location_id: Int = 0,

    var timestamp: Long,

    var ride_id: Int,

    var longitude: Double,

    var latitude: Double,

    var is_uploaded: Int
) : Serializable