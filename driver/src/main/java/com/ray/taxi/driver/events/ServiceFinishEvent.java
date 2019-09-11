package com.ray.taxi.driver.events;

import com.innomalist.taxi.common.events.BaseRequestEvent;
import com.innomalist.taxi.common.utils.ServerResponse;
import com.ray.taxi.driver.models.FinishService;

public class ServiceFinishEvent extends BaseRequestEvent {
    public FinishService finishService;
    public ServiceFinishEvent(FinishService finishService) {
        super(new ServiceFinishResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue()));
        this.finishService = finishService;
    }
}
