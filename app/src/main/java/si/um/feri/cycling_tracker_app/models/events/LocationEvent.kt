package si.um.feri.cycling_tracker_app.models.events

import android.location.Location

data class LocationEvent(
    var longitude: Double,

    var latitude: Double,

    var location: Location
)
