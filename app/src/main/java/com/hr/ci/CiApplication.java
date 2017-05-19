package com.hr.ci;

import android.app.Application;

public class CiApplication extends Application {

    public static CiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static CiApplication getInstance() {
        return instance;
    }
}
