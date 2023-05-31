package si.um.feri.cycling_tracker_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val user_id : Int = 0,

    val username : String,

    val token : String
) : Serializable