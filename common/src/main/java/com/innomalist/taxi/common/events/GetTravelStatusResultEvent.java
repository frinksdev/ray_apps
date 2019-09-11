package com.innomalist.taxi.common.events;

import com.google.android.gms.maps.model.LatLng;
import com.innomalist.taxi.common.models.Travel;

import org.json.JSONException;
import org.json.JSONObject;

public class GetTravelStatusResultEvent extends BaseResultEvent {
    public Travel travel;
    public LatLng driverLocation;

    public GetTravelStatusResultEvent(Object... args) {
        super(args);
        this.travel = Travel.fromJson(args[0].toString());
        if(args.length > 1) {
            try {
                JSONObject _l = new JSONObject(args[1].toString());
                this.driverLocation = new LatLng(_l.getDouble("y"), _l.getDouble("x"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
