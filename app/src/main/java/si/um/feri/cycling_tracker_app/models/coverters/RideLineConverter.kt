package si.um.feri.cycling_tracker_app.models.coverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class RideLineConverter {
    @TypeConverter
    fun fromString(value: String?): List<List<Double>> {
        val listType: Type = object : TypeToken<List<List<Double>>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<List<Double>>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}