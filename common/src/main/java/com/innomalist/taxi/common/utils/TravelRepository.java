package com.innomalist.taxi.common.utils;

import android.content.Context;

import com.innomalist.taxi.common.models.Travel;

public class TravelRepository {
    public static Travel get(Context context, AppType app) {
        Travel travel = Travel.fromJson(MyPreferenceManager.getInstance(context).getString(app.value + "_travel", "{}"));
        return travel;
    }

    public static void set(Context context, AppType app, Travel travel) {
        MyPreferenceManager.getInstance(context).putString(app.value + "_travel", travel.toJson());
    }

    public enum AppType {
        DRIVER("driver"),
        RIDER("rider");
        private String value;
        AppType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public static AppType get(String code) {
            for(AppType s : values()) {
                if(s.value.equals(code)) return s;
            }
            return DRIVER;
        }
    }
}
