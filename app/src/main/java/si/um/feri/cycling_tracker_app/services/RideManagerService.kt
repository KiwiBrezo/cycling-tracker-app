package si.um.feri.cycling_tracker_app.services

import android.content.Context
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.models.RideDataLocation
import si.um.feri.cycling_tracker_app.utils.AppDatabase

class RideManagerService private constructor(context: Context) {

    private var rideDatabase: AppDatabase = AppDatabase.getInstance(context)

    companion object {
        private var instance: RideManagerService? = null

        fun getInstance(context: Context): RideManagerService {
            if (instance == null) {
                instance = RideManagerService(context)
            }
            return instance!!
        }
    }

    fun startRideLocation(userId: Int, startedTime: Long): RideData {
        var newRideData = RideData(
            timeStart = startedTime,
            timeStop = -1L,
            duration = 0L,
            user_id = userId,
            rideLine = mutableListOf(listOf()),
            is_active = 1,
            is_uploaded = 0
        )

        this.rideDatabase.rideDataDao().insert(newRideData)

        return rideDatabase.rideDataDao().getActiveRide()
    }

    fun saveRideLocation(rideId: Int, userId: Int, latitude: Double, longitude: Double) : RideDataLocation {
        var rideLocation = RideDataLocation(
            timestamp = System.currentTimeMillis(),
            ride_id = rideId,
            latitude = latitude,
            longitude = longitude,
            is_uploaded = 0
        )

        this.rideDatabase.rideDataLocation().insert(rideLocation)

        return rideLocation
    }

    fun stopRideLocation(rideId: Int, stopedTime: Long, duration: Long): RideData {
        var thisRideData = this.rideDatabase.rideDataDao().getRideById(rideId)
        thisRideData.is_active = 0
        thisRideData.duration = duration
        thisRideData.timeStop = stopedTime

        var rideDataLocations = this.rideDatabase.rideDataLocation().getAllRideLocationDataForRide(thisRideData.ride_id)

        thisRideData.rideLine = mutableListOf<List<Double>>()
        rideDataLocations.forEach {
            thisRideData.rideLine.add(listOf(it.latitude, it.longitude))
            this.rideDatabase.rideDataLocation().delete(it)
        }

        this.rideDatabase.rideDataDao().update(thisRideData)

        return thisRideData
    }
}