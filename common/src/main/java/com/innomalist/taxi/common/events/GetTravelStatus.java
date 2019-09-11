package com.innomalist.taxi.common.events;

public class GetTravelStatus {
    public long travelId;
    public GetTravelStatus(long travelId) {
        this.travelId = travelId;
    }
}
