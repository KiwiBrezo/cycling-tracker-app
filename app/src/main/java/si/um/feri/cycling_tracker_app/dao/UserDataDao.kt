package si.um.feri.cycling_tracker_app.dao

import androidx.room.*
import si.um.feri.cycling_tracker_app.models.UserData

@Dao
interface UserDataDao {
    @Insert
    fun insert(userData: UserData): Long

    @Update
    fun update(userData: UserData)

    @Delete
    fun delete(userData: UserData)

    @Query("SELECT * FROM user")
    fun getAllUser(): MutableList<UserData>

    @Query("SELECT * FROM user WHERE user_id = :userId")
    fun getUserDataById(userId: Int): UserData

    @Query("SELECT * FROM user WHERE token = :token")
    fun getUserDataByToken(token: String): UserData
}