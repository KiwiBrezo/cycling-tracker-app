package si.um.feri.cycling_tracker_app.dao

import androidx.room.*
import si.um.feri.cycling_tracker_app.models.RideDataLocation

@Dao
interface RideDataLocationDao {
    @Insert
    fun insert(userData: RideDataLocation): Long

    @Update
    fun update(userData: RideDataLocation)

    @Delete
    fun delete(userData: RideDataLocation)

    @Query("SELECT * FROM ride_location")
    fun getAllRideLocationData(): MutableList<RideDataLocation>

    @Query("SELECT * FROM ride_location WHERE ride_id = :rideId")
    fun getAllRideLocationDataForRide(rideId: Int): MutableList<RideDataLocation>

    @Query("SELECT * FROM ride_location WHERE is_uploaded = 0")
    fun getAllRideLocationDataNotUploaded(): MutableList<RideDataLocation>

    @Query("SELECT * FROM ride_location WHERE is_uploaded = 1")
    fun getAllRideLocationDataUploaded(): MutableList<RideDataLocation>
}