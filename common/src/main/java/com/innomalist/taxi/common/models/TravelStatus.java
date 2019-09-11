package com.innomalist.taxi.common.models;

import com.google.gson.annotations.SerializedName;
import com.innomalist.taxi.common.utils.TravelRepository;

public enum  TravelStatus {
    @SerializedName("requested")
    REQUESTED("requested"),

    @SerializedName("not found")
    NOT_FOUND("not found"),

    @SerializedName("found")
    FOUND("found"),

    @SerializedName("driver accepted")
    DRIVER_ACCEPTED("driver accepted"),

    @SerializedName("rider accepted")
    RIDER_ACCEPTED("rider accepted"),

    @SerializedName("travel started")
    TRAVEL_STARTED("travel started"),

    @SerializedName("rider canceled")
    RIDER_CANCELED("rider canceled"),

    @SerializedName("driver canceled")
    DRIVER_CANCELED("driver canceled"),

    @SerializedName("booked")
    BOOKED("booked"),

    @SerializedName("travel finished cash")
    TRAVEL_FINISHED_CASH("travel finished cash"),

    @SerializedName("travel finished credit")
    TRAVEL_FINISHED_CREDIT("travel finished credit"),

    @SerializedName("expired")
    EXPIRED("expired");

    private String value;

    TravelStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public static TravelStatus get(String code) {
        for(TravelStatus s : values()) {
            if(s.value.equals(code)) return s;
        }
        return FOUND;
    }
}
