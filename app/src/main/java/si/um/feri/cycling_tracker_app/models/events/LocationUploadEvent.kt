package si.um.feri.cycling_tracker_app.models.events

data class LocationUploadEvent(
    val location_id: Int,
    val ride_id: Int,
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double
)
