package com.example.joelercoaster.droidlett;

import android.app.Application;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;

public class IntercomApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Intercom.initialize(this, "android_sdk-804c09a3d3ab19a985c151fe226a661d14a0ca2c", "gkhipr3y");
    }

}
