package com.hr.ci.home.activities;

import android.view.View;


public class MainTestActivity extends MainActivity {

        public boolean isIdle() {
            return progress.getVisibility() != View.VISIBLE;
        }



}
