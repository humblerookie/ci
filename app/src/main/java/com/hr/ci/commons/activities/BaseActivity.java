package com.hr.ci.commons.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate: %s", this.toString());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume: %s", this.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop: %s", this.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart: %s", this.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause: %s", this.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy: %s", this.toString());
    }


    protected boolean isAlive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !isFinishing() && !isDestroyed();
        } else {
            return !isFinishing();
        }
    }
}
