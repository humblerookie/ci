package com.hr.ci.commons.logging;

import timber.log.Timber;

public class DebugLogTree extends Timber.DebugTree {

    public static final int MAX_LENGTH = 150;

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return super.createStackElementTag(element) + "-" + element.getLineNumber();
    }


}
