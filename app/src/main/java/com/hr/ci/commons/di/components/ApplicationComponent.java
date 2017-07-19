package com.hr.ci.commons.di.components;

import android.app.Application;
import android.content.Context;

import com.hr.ci.CiApplication;
import com.hr.ci.commons.di.modules.ApplicationModule;
import com.hr.ci.commons.di.modules.NetworkModule;
import com.hr.ci.commons.di.modules.SchedulersModule;
import com.hr.ci.commons.di.scopes.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, SchedulersModule.class})
public interface ApplicationComponent {

    void inject(CiApplication ciApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    Retrofit getRetrofit();


}
