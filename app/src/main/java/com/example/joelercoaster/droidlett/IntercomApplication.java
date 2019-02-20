package com.example.joelercoaster.droidlett;

import android.app.Application;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;

public class IntercomApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Intercom.initialize(this, BuildConfig.API_KEY, BuildConfig.APP_ID);
    }

}
