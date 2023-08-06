package si.um.feri.cycling_tracker_app.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import si.um.feri.cycling_tracker_app.grpc.AddLocationRequest
import si.um.feri.cycling_tracker_app.grpc.AddRideLocationServiceGrpcKt
import si.um.feri.cycling_tracker_app.grpc.AddRideRequest
import si.um.feri.cycling_tracker_app.grpc.AddRideServiceGrpcKt
import si.um.feri.cycling_tracker_app.models.events.LocationIsUploadedEvent
import si.um.feri.cycling_tracker_app.models.events.LocationUploadEvent
import si.um.feri.cycling_tracker_app.models.events.RideIsUploadedEvent
import si.um.feri.cycling_tracker_app.models.events.RideUploadEvent

class RideUploadService : Service() {

    private lateinit var chanel: ManagedChannel
    private lateinit var rideStub: AddRideServiceGrpcKt.AddRideServiceCoroutineStub
    private lateinit var locationStub: AddRideLocationServiceGrpcKt.AddRideLocationServiceCoroutineStub

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)

        this.chanel = ManagedChannelBuilder.forAddress("10.0.2.2", 8081).usePlaintext().build()
        this.rideStub = AddRideServiceGrpcKt.AddRideServiceCoroutineStub(this.chanel)
        this.locationStub = AddRideLocationServiceGrpcKt.AddRideLocationServiceCoroutineStub(this.chanel)
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun uploadNewRideToServer(rideUploadEvent: RideUploadEvent) {
        runBlocking {
            val request = AddRideRequest
                .newBuilder()
                .setRideId(rideUploadEvent.ride_id)
                .setUserId(rideUploadEvent.user_id)
                .setDuration(rideUploadEvent.duration)
                .setTimeStart(rideUploadEvent.timeStart)
                .setTimeStop(rideUploadEvent.timeStop)
                .build()

            val response = rideStub.addRide(request)

            if (response.isUploaded > 0) {
                Log.i("GRPC RIDE UPLOAD", "Ride was successfully uploaded")
                EventBus.getDefault().post(RideIsUploadedEvent(rideUploadEvent.ride_id))
            } else {
                Log.i("GRPC RIDE UPLOAD", "There was an error while uploading the ride data")
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun uploadNewRideLocationToServer(locationUploadEvent: LocationUploadEvent) {
        runBlocking {
            val request = AddLocationRequest
                .newBuilder()
                .setRideId(locationUploadEvent.ride_id)
                .setTimestamp(locationUploadEvent.timestamp)
                .setLatitude(locationUploadEvent.latitude)
                .setLongitude(locationUploadEvent.longitude)
                .build()

            val response = locationStub.addRideLocation(request)

            if (response.isUploaded > 0) {
                Log.i("GRPC LOCATION UPLOAD", "Ride location was successfully uploaded")
                EventBus.getDefault().post(LocationIsUploadedEvent(locationUploadEvent.location_id))
            } else {
                Log.i("GRPC LOCATION UPLOAD", "There was an error while uploading the ride location")
            }
        }
    }
}