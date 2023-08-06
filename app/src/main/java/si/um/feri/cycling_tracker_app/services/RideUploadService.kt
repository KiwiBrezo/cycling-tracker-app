package si.um.feri.cycling_tracker_app.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import si.um.feri.cycling_tracker_app.AppController
import si.um.feri.cycling_tracker_app.R
import si.um.feri.cycling_tracker_app.grpc.AddLocationRequest
import si.um.feri.cycling_tracker_app.grpc.AddRideLocationServiceGrpcKt
import si.um.feri.cycling_tracker_app.grpc.AddRideRequest
import si.um.feri.cycling_tracker_app.grpc.AddRideServiceGrpcKt
import si.um.feri.cycling_tracker_app.models.events.*
import java.util.concurrent.TimeUnit

class RideUploadService : Service() {
    private val appController = AppController.applicationContext()

    private var hasInternetConnection = true;
    private var hasGrpcConnection = true;
    private var needToTryUploadAgain = false;
    private var needToConnectToGrpcAgain = false;

    private lateinit var chanel: ManagedChannel
    private lateinit var rideStub: AddRideServiceGrpcKt.AddRideServiceCoroutineStub
    private lateinit var locationStub: AddRideLocationServiceGrpcKt.AddRideLocationServiceCoroutineStub

    private lateinit var periodicInternetCheckerHandler: Handler;
    private val delay = 500 // 0.5 second

    private var periodicInternetChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                hasInternetConnection = appController.isNetworkAvailable(this@RideUploadService)
                Log.i("GRPC INTERNET CHECK", "Is there internet connection: $hasInternetConnection")

                // if there is no internet the we ne to set flag that stuff will be needed to upload again
                if (!hasInternetConnection && !needToTryUploadAgain) {
                    needToTryUploadAgain = true
                    Toast.makeText(this@RideUploadService, R.string.lost_connection, Toast.LENGTH_SHORT).show()
                }

                // if internet is back try to upload stuff again
                if (hasInternetConnection && needToTryUploadAgain) {
                    needToTryUploadAgain = false
                    connectToGrpcServer()
                    EventBus.getDefault().post(BackOnlineEvent(hasInternetConnection))
                    Toast.makeText(this@RideUploadService, R.string.connected_back, Toast.LENGTH_SHORT).show()
                }

                if (!hasGrpcConnection) {
                    connectToGrpcServer()
                }

                if (hasGrpcConnection && needToConnectToGrpcAgain) {
                    EventBus.getDefault().post(BackOnlineEvent(hasInternetConnection))
                }
            } finally {
                periodicInternetCheckerHandler.postDelayed(this, delay.toLong())
            }
        }
    }

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

        connectToGrpcServer()

        periodicInternetCheckerHandler = Handler(Looper.getMainLooper())
        periodicInternetChecker.run()
    }

    override fun onDestroy() {
        super.onDestroy()
        periodicInternetCheckerHandler.removeCallbacks(periodicInternetChecker)
        EventBus.getDefault().unregister(this)
    }

    private fun connectToGrpcServer() {
        try {
            this.chanel = ManagedChannelBuilder.forAddress("10.0.2.2", 8081).usePlaintext().build()
            this.rideStub = AddRideServiceGrpcKt.AddRideServiceCoroutineStub(this.chanel)
            this.locationStub = AddRideLocationServiceGrpcKt.AddRideLocationServiceCoroutineStub(this.chanel)
        } catch (e: Exception) {
            Log.e("GRPC SERVER CONNECT", "There was an problem connecting to the gRPC server, maybe it is down!")
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun uploadNewRideToServer(rideUploadEvent: RideUploadEvent) {
        if (!hasInternetConnection) {
            Log.i("GRPC RIDE UPLOAD", "There is no internet connection")
            return
        }

        runBlocking {
            val request = AddRideRequest
                .newBuilder()
                .setRideId(rideUploadEvent.ride_id)
                .setUserId(rideUploadEvent.user_id)
                .setDuration(rideUploadEvent.duration)
                .setTimeStart(rideUploadEvent.timeStart)
                .setTimeStop(rideUploadEvent.timeStop)
                .build()

            try {
                val response = rideStub.withDeadlineAfter(300, TimeUnit.MILLISECONDS).addRide(request)

                if (response.isUploaded > 0) {
                    Log.i("GRPC RIDE UPLOAD", "Ride was successfully uploaded")
                    EventBus.getDefault().post(RideIsUploadedEvent(rideUploadEvent.ride_id))
                } else {
                    Log.i("GRPC RIDE UPLOAD", "There was an error while uploading the ride data")
                }

                hasGrpcConnection = true
            } catch (e: Exception) {
                needToConnectToGrpcAgain = true
                hasGrpcConnection = false
                Log.e("GRPC RIDE UPLOAD", "gRPC server is not reachable!")
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun uploadNewRideLocationToServer(locationUploadEvent: LocationUploadEvent) {
        if (!hasInternetConnection) {
            Log.i("GRPC LOCATION UPLOAD", "There is no internet connection")
            return
        }

        runBlocking {
            val request = AddLocationRequest
                .newBuilder()
                .setRideId(locationUploadEvent.ride_id)
                .setTimestamp(locationUploadEvent.timestamp)
                .setLatitude(locationUploadEvent.latitude)
                .setLongitude(locationUploadEvent.longitude)
                .build()

            try {
                val response = locationStub.withDeadlineAfter(300, TimeUnit.MILLISECONDS).addRideLocation(request)

                if (response.isUploaded > 0) {
                    Log.i("GRPC LOCATION UPLOAD", "Ride location was successfully uploaded")
                    EventBus.getDefault().post(LocationIsUploadedEvent(locationUploadEvent.location_id))
                } else {
                    Log.i("GRPC LOCATION UPLOAD", "There was an error while uploading the ride location")
                }

                hasGrpcConnection = true
            } catch (e: Exception) {
                needToConnectToGrpcAgain = true
                hasGrpcConnection = false
                Log.e("GRPC LOCATION UPLOAD", "gRPC server is not reachable!")
            }
        }
    }
}