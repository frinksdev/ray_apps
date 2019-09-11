package com.ray.taxi.rider.events;

import com.innomalist.taxi.common.events.BaseResultEvent;

public class ServiceFinishedEvent extends BaseResultEvent {
    public boolean isCreditUsed;
    public Double amount;
    public ServiceFinishedEvent(int code, boolean isCreditUsed, Double amount){
        super(code);
        this.isCreditUsed = isCreditUsed;
        this.amount = amount;
    }
}
