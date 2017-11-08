package com.example.drewv.geocalculator;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by drewv on 11/3/2017.
 */

public class GeoCalculatorApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
