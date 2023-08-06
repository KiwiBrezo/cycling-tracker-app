package si.um.feri.cycling_tracker_app.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import si.um.feri.cycling_tracker_app.dao.RideDataDao
import si.um.feri.cycling_tracker_app.dao.RideDataLocationDao
import si.um.feri.cycling_tracker_app.dao.UserDataDao
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.models.RideDataLocation
import si.um.feri.cycling_tracker_app.models.UserData
import si.um.feri.cycling_tracker_app.models.coverters.RideLineConverter

@Database(entities = [RideData::class, RideDataLocation::class, UserData::class], version = 1)
@TypeConverters(RideLineConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rideDataDao(): RideDataDao
    abstract fun userDataDao(): UserDataDao
    abstract fun rideDataLocationDao(): RideDataLocationDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cycling_tracker_app_database"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}