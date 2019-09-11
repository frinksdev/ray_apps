package com.ray.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class GetTravelInfoResultEvent {
    public LatLng location;
    public GetTravelInfoResultEvent(Object... args){
        JSONObject _l;
        try {
            _l = new JSONObject(args[0].toString());
            this.location = new LatLng(_l.getDouble("y"), _l.getDouble("x"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
