package com.ray.taxi.driver.events;

public class CancelRequestEvent {
    public int travelId;

    public CancelRequestEvent(Object... args) {
        this.travelId = (int)args[0];
    }
}
