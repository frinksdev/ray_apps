package com.ray.taxi.driver.events;

import com.google.android.gms.maps.model.LatLng;

public class LocationChangedEvent {
    public LatLng location;
    public LocationChangedEvent(LatLng location){
        this.location = location;
    }
}
