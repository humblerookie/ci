package com.hr.ci;

import com.hr.ci.commons.logging.ReleaseLogTree;

import timber.log.Timber;

public class CiApplication extends BaseApplication {

    public static CiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    protected Timber.DebugTree provideLogger() {
        return new ReleaseLogTree();
    }

    public static CiApplication getInstance() {
        return instance;
    }


}
