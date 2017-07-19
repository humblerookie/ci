package com.hr.ci;

import com.hr.ci.commons.di.components.ApplicationComponent;
import com.hr.ci.commons.di.components.DaggerApplicationComponent;
import com.hr.ci.commons.di.modules.ApplicationModule;
import com.hr.ci.commons.di.modules.NetworkModule;
import com.hr.ci.commons.injectors.NetworkModuleInjector;
import com.hr.ci.commons.logging.DebugLogTree;
import com.hr.ci.commons.util.Constants;

import timber.log.Timber;

public class CiApplication extends BaseApplication {

    public static CiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initTestResources();
        ApplicationComponent component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule()).build();
    }

    private void initTestResources() {
        NetworkModuleInjector.setBaseUrl(Constants.BASE_URL);
    }

    protected Timber.DebugTree provideLogger() {
        return new DebugLogTree();
    }

    public static CiApplication getInstance() {
        return instance;
    }


}
