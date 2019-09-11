package com.ray.taxi.driver.events;

import com.innomalist.taxi.common.events.BaseRequestEvent;
import com.innomalist.taxi.common.utils.ServerResponse;

public class PaymentRequestEvent extends BaseRequestEvent {
    public PaymentRequestEvent() {
        super(new PaymentRequestResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue()));
    }
}
