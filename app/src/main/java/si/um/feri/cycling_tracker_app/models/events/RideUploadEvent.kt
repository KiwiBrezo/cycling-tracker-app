package si.um.feri.cycling_tracker_app.models.events

data class RideUploadEvent(
    val ride_id: Int,
    val timeStart: Long,
    val timeStop: Long,
    val duration: Long,
    val user_id: Int
)
