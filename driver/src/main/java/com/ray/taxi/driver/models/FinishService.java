package com.ray.taxi.driver.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class FinishService {
    private String log;
    private double cost;

    @SerializedName("confirmation_code")
    private int confirmationCode;

    private int distance;

    public FinishService(String log, double cost, int distance) {
        this.log = log;
        this.cost = cost;
        this.distance = distance;
    }

    public FinishService(String log, double cost, int distance, int confirmationCode) {
        this.log = log;
        this.cost = cost;
        this.confirmationCode = confirmationCode;
        this.distance = distance;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(int confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public static FinishService fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(json,FinishService.class);
    }

    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();
        return customGson.toJson(this);
    }
}
