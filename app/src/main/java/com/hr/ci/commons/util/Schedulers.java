package com.hr.ci.commons.util;

import io.reactivex.Scheduler;

public interface Schedulers {

    Scheduler io();

    Scheduler compute();

    Scheduler ui();

}
