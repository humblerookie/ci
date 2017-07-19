package com.hr.ci.commons.di.modules;

import javax.inject.Named;

import dagger.Module;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {

    public static final String IO = "io";
    public static final String COMPUTE = "compute";
    public static final String UI = "ui";

    @Named(IO)
    public Scheduler getIo() {
        return Schedulers.io();
    }

    @Named(COMPUTE)
    public Scheduler getCompute() {
        return Schedulers.computation();
    }

    @Named(UI)
    public Scheduler getUi() {
        return AndroidSchedulers.mainThread();
    }
}
