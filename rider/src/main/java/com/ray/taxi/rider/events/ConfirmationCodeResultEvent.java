package com.ray.taxi.rider.events;

import com.innomalist.taxi.common.events.BaseResultEvent;

public class ConfirmationCodeResultEvent extends BaseResultEvent {
    public int code;
    public ConfirmationCodeResultEvent(Object... args) {
        super(args);
        if(!hasError()) {
            code = (int)args[1];
        }
    }
}
