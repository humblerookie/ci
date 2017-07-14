package com.hr.ci.commons.logging;

import android.util.Log;

import timber.log.Timber;

public class ReleaseLogTree extends Timber.DebugTree {
    public static final int MAX_LENGTH = 150;

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return super.createStackElementTag(element) + "-" + element.getLineNumber();
    }


    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (isLoggable(tag, priority)) {
            if (message.length() < MAX_LENGTH) {
                assertAndLog(priority, tag, message);
            } else {
                for (int i = 0, length = message.length(); i < length; i++) {
                    int newLineIndex = message.indexOf("\n", i);
                    newLineIndex = newLineIndex != -1 ? newLineIndex : length;
                    do {
                        int end = Math.min(newLineIndex, i + MAX_LENGTH);
                        String part = message.substring(i, end);
                        assertAndLog(priority, tag, part);
                        i = end;
                    } while (i < newLineIndex);

                }
            }
        }
    }

    private void assertAndLog(int priority, String tag, String message) {
        if (priority == Log.ASSERT) {
            super.wtf(tag, message);
        } else {
            Log.println(priority, tag, message);
        }
    }

    @Override
    protected boolean isLoggable(String tag, int priority) {

        if (priority == Log.ERROR || priority == Log.WARN) {
            return true;
        } else {
            return false;
        }

    }

}
