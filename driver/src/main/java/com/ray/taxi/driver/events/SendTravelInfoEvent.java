package com.ray.taxi.driver.events;

import com.google.android.gms.maps.model.LatLng;

public class SendTravelInfoEvent {
    public LatLng location;
    public SendTravelInfoEvent(LatLng location) {
        this.location = location;
    }
}
