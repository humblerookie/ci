package com.hr.ci;

import com.hr.ci.commons.logging.DebugLogTree;

import timber.log.Timber;

public class CiApplication extends BaseApplication {

    public static CiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    protected Timber.DebugTree provideLogger() {
        return new DebugLogTree();
    }

    public static CiApplication getInstance() {
        return instance;
    }


}
