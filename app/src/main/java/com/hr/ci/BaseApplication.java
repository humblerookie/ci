package com.hr.ci;

import android.app.Application;

import timber.log.Timber;

public abstract class BaseApplication extends Application {

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeComponents();
    }

    private void initializeComponents() {
        Timber.plant(provideLogger());
    }

    protected abstract Timber.Tree provideLogger();


}
