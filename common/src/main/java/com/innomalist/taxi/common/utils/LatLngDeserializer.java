package com.innomalist.taxi.common.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class LatLngDeserializer implements JsonDeserializer<LatLng>, JsonSerializer<LatLng> {
    @Override
    public LatLng deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {

        JsonObject jsonObject = json.getAsJsonObject();

        if(jsonObject.get("y") != null)
            return new LatLng(
                jsonObject.get("y").getAsDouble(),
                jsonObject.get("x").getAsDouble());
        else {
            if(jsonObject.get("latitude") != null) {
                return new LatLng(
                        jsonObject.get("latitude").getAsDouble(),
                        jsonObject.get("longitude").getAsDouble());
            } else {
                return null;
            }

        }
    }

    @Override
    public JsonElement serialize(LatLng src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", src.longitude);
        jsonObject.addProperty("y", src.latitude);
        return jsonObject;
    }
}