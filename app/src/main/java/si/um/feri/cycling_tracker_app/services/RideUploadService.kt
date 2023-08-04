package si.um.feri.cycling_tracker_app.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RideUploadService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}