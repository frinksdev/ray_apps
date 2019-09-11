package com.ray.taxi.driver.events;

import com.innomalist.taxi.common.events.BaseResultEvent;

public class ChangeStatusResultEvent extends BaseResultEvent {
    public ChangeStatusResultEvent(int code) {
        super(code);
    }
}
