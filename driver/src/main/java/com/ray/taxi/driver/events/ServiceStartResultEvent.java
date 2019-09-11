package com.ray.taxi.driver.events;

import com.innomalist.taxi.common.events.BaseResultEvent;
import com.innomalist.taxi.common.models.Travel;

public class ServiceStartResultEvent extends BaseResultEvent {
    public Travel travel;

    public ServiceStartResultEvent(Object... args) {
        super(args);
        this.travel = Travel.fromJson(args[0].toString());
    }
}
