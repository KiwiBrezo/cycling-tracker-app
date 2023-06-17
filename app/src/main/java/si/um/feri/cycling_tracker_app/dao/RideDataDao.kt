package si.um.feri.cycling_tracker_app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import si.um.feri.cycling_tracker_app.models.RideData

@Dao
interface RideDataDao {
    @Insert
    fun insert(rideData: RideData): Long

    @Update
    fun update(rideData: RideData)

    @Delete
    fun delete(rideData: RideData)

    @Query("SELECT * FROM ride")
    fun getAllRideData(): MutableList<RideData>

    @Query("SELECT * FROM ride WHERE ride_id = :rideId")
    fun getRideById(rideId: Int): RideData
}