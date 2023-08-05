package si.um.feri.cycling_tracker_app.models.events

data class LocationUploadEvent(
    var ride_id: Int,
    var timestamp: Long,
    var latitude: Double,
    var longitude: Double
)
