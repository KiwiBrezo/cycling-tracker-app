package si.um.feri.cycling_tracker_app.models.events

data class RideUploadEvent(
    var ride_id: Int,
    var timeStart: Long,
    var timeStop: Long,
    var duration: Long,
    var user_id: Int
)
