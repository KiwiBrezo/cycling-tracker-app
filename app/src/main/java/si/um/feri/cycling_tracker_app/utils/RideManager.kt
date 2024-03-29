package si.um.feri.cycling_tracker_app.utils

import android.content.Context
import android.util.Log
import si.um.feri.cycling_tracker_app.models.RideData
import si.um.feri.cycling_tracker_app.models.RideDataLocation

class RideManager private constructor(context: Context) {

    private var rideDatabase: AppDatabase = AppDatabase.getInstance(context)

    companion object {
        private var instance: RideManager? = null

        fun getInstance(context: Context): RideManager {
            if (instance == null) {
                instance = RideManager(context)
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

        val insertedId = this.rideDatabase.rideDataDao().insert(newRideData)

        Log.i("Ride Manager", "Saved new starting ride with id: $insertedId to local database")

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

        val insertedId = this.rideDatabase.rideDataLocationDao().insert(rideLocation).toInt()

        Log.i("Ride Manager", "Saved new ride location with id: $insertedId to local database")

        return this.rideDatabase.rideDataLocationDao().getRideDataLocationById(insertedId)
    }

    fun stopRideLocation(rideId: Int, stopedTime: Long, duration: Long): RideData {
        var thisRideData = this.rideDatabase.rideDataDao().getRideById(rideId)
        thisRideData.is_active = 0
        thisRideData.duration = duration
        thisRideData.timeStop = stopedTime

        var rideDataLocations = this.rideDatabase.rideDataLocationDao().getAllRideLocationDataForRide(thisRideData.ride_id)

        thisRideData.rideLine = mutableListOf<List<Double>>()
        rideDataLocations.forEach {
            thisRideData.rideLine.add(listOf(it.latitude, it.longitude))
            this.rideDatabase.rideDataLocationDao().delete(it)
        }

        this.rideDatabase.rideDataDao().update(thisRideData)

        Log.i("Ride Manager", "Updated final state of ride with id: ${thisRideData.ride_id} to local database")

        return thisRideData
    }

    fun checkForNotEndedRide(): MutableList<RideDataLocation> {
        Log.i("Ride Manager", "Checking if there are some not finished rides to bi completed...")

        var notUploadedLocations = mutableListOf<RideDataLocation>()
        var rideDataLocations = this.rideDatabase.rideDataLocationDao().getAllRideLocationData()

        if (rideDataLocations.size == 0) {
            return mutableListOf()
        }

        Log.i("Ride Manager", "Found one not finished ride ... completing it now")

        var ride = this.rideDatabase.rideDataDao().getRideById(rideDataLocations[0].ride_id)

        ride.is_active = 0
        ride.duration = (rideDataLocations.last().timestamp - ride.timeStart) / 1000
        ride.timeStop = rideDataLocations.last().timestamp

        ride.rideLine = mutableListOf<List<Double>>()
        rideDataLocations.forEach {
            ride.rideLine.add(listOf(it.latitude, it.longitude))
            if (it.is_uploaded == 0) {
                notUploadedLocations.add(it)
            } else {
                this.rideDatabase.rideDataLocationDao().delete(it)
            }
        }

        this.rideDatabase.rideDataDao().update(ride)

        Log.i("Ride Manager", "Need to upload ${notUploadedLocations.size} ride locations to server")

        return notUploadedLocations
    }

    fun getAllRideLocationsNotUploaded(): MutableList<RideDataLocation> {
        return this.rideDatabase.rideDataLocationDao().getAllRideLocationDataNotUploaded()
    }

    fun getAllFinishedRidesNotUploaded(): MutableList<RideData> {
        return this.rideDatabase.rideDataDao().getAllNotUploadedAndIsFinished()
    }

    fun setRideLocationStatusToUploaded(locationId: Int): RideDataLocation {
        var location = this.rideDatabase.rideDataLocationDao().getRideDataLocationById(locationId)
        val rideOfLocation = this.rideDatabase.rideDataDao().getRideById(location.ride_id)

        // If ride is not active then there is no reason for keeping location in local database
        if (rideOfLocation.is_active == 0) {
            this.rideDatabase.rideDataLocationDao().delete(location)
        } else {
            location.is_uploaded = 1
            this.rideDatabase.rideDataLocationDao().update(location)
        }

        return location
    }

    fun setRideStatusToUploaded(rideId: Int): RideData {
        var ride = this.rideDatabase.rideDataDao().getRideById(rideId)

        ride.is_uploaded = 1

        this.rideDatabase.rideDataDao().update(ride)

        return ride
    }
}