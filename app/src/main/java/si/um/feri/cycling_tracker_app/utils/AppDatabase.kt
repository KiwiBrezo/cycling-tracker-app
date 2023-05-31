package si.um.feri.cycling_tracker_app.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.models.RideDataLocation
import si.um.feri.cycling_tracker_app.models.UserData
import si.um.feri.cycling_tracker_app.models.coverters.RideLineConverter

@Database(entities = [RideData::class, RideDataLocation::class, UserData::class], version = 1)
@TypeConverters(RideLineConverter::class)
abstract class AppDatabase : RoomDatabase() {
}