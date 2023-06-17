package si.um.feri.cycling_tracker_app.services

import android.content.Context
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

    fun startRideLocation() {
        // TODO
    }

    fun saveRideLocation() {
        // TODO
    }

    fun stopRideLocation() {
        // TODO
    }
}