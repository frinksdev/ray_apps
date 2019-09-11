package com.ray.taxi.driver.events;

import com.innomalist.taxi.common.events.BaseResultEvent;

public class ServiceFinishResultEvent extends BaseResultEvent {
    public boolean isCreditUsed;
    public double amount;
    public ServiceFinishResultEvent(Object... args){
        super(args);
        if(hasError())
            return;
        this.isCreditUsed = (boolean)args[1];
        this.amount = Double.parseDouble(args[2].toString());
    }
}
