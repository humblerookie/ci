package com.hr.ci.commons.injectors;

import com.hr.ci.commons.util.Schedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SchedulerInjector {

    static Schedulers schedulers;

    public static Schedulers getSchedulers() {

        if (schedulers == null) {
            schedulers = new Schedulers() {
                @Override
                public Scheduler io() {
                    return io.reactivex.schedulers.Schedulers.io();
                }

                @Override
                public Scheduler compute() {
                    return io.reactivex.schedulers.Schedulers.computation();
                }

                @Override
                public Scheduler ui() {
                    return AndroidSchedulers.mainThread();
                }
            };
        }
        return schedulers;
    }
}
