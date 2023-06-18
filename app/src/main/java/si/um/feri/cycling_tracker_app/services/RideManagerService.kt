package si.um.feri.cycling_tracker_app.services

import android.content.Context
import si.um.feri.cycling_tracker_app.models.RideData
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
            rideLine = listOf(listOf()),
            is_active = 1,
            is_uploaded = 0
        )

        this.rideDatabase.rideDataDao().insert(newRideData)

        return rideDatabase.rideDataDao().getActiveRide()
    }

    fun saveRideLocation(rideId: Int, userId: Int) {
        // TODO
    }

    fun stopRideLocation(rideId: Int, stopedTime: Long, duration: Long): RideData {
        var thisRideData = this.rideDatabase.rideDataDao().getRideById(rideId)
        thisRideData.is_active = 0
        thisRideData.duration = duration
        thisRideData.timeStop = stopedTime

        // TODO need magic for converting the point to the line

        this.rideDatabase.rideDataDao().update(thisRideData)

        return thisRideData
    }
}