package si.um.feri.cycling_tracker_app.models.events

import android.location.Location

data class LocationEvent(
    val longitude: Double,
    val latitude: Double,
    val location: Location
)
