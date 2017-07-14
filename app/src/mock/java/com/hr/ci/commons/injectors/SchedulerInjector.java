package com.hr.ci.commons.injectors;

import com.hr.ci.commons.util.Schedulers;

import io.reactivex.Scheduler;

public class SchedulerInjector {

    static Schedulers schedulers;

    public static Schedulers getSchedulers() {

        if (schedulers == null) {
            schedulers = new Schedulers() {
                @Override
                public Scheduler io() {
                    return io.reactivex.schedulers.Schedulers.trampoline();
                }

                @Override
                public Scheduler compute() {
                    return io.reactivex.schedulers.Schedulers.trampoline();
                }

                @Override
                public Scheduler ui() {
                    return io.reactivex.schedulers.Schedulers.trampoline();
                }
            };
        }
        return schedulers;
    }
}
