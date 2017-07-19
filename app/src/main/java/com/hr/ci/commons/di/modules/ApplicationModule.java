package com.hr.ci.commons.di.modules;

import android.app.Application;
import android.content.Context;

import com.hr.ci.CiApplication;
import com.hr.ci.commons.di.scopes.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final CiApplication application;

    public ApplicationModule(CiApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }


}
