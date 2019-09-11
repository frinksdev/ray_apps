package com.ray.taxi.rider.events;

import com.google.gson.Gson;
import com.innomalist.taxi.common.models.Travel;

public class DriverAcceptedEvent {
    public Travel travel;
    public DriverAcceptedEvent(Object... args) {
        Gson gson = new Gson();
        travel = gson.fromJson(args[0].toString(), Travel.class);
    }
}
