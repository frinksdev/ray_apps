package com.ray.taxi.rider.events;

import com.innomalist.taxi.common.models.Travel;

public class ServiceStartedEvent {
    public Travel travel;
    public ServiceStartedEvent(Object... args) {
        this.travel = Travel.fromJson(args[0].toString());
    }
}
