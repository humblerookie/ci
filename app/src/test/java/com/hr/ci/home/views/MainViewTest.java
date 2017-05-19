package com.hr.ci.home.views;


import android.view.View;

import com.hr.ci.BuildConfig;
import com.hr.ci.R;
import com.hr.ci.home.activities.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainViewTest {

    private MainActivity mainActivity;

    private void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void viewsRequiredArePresentInitially() {
        setUp();
        assertEquals(mainActivity.findViewById(R.id.list).getVisibility(), View.VISIBLE);
        assertEquals(mainActivity.findViewById(R.id.toolbar).getVisibility(), View.VISIBLE);
        assertEquals(mainActivity.findViewById(R.id.error_text).getVisibility(), View.GONE);
        assertEquals(mainActivity.findViewById(R.id.progress).getVisibility(), View.VISIBLE);
    }

    @Test
    public void presenter() {
        setUp();
        assertEquals(mainActivity.findViewById(R.id.list).getVisibility(), View.VISIBLE);
        assertEquals(mainActivity.findViewById(R.id.toolbar).getVisibility(), View.VISIBLE);
        assertEquals(mainActivity.findViewById(R.id.error_text).getVisibility(), View.GONE);
        assertEquals(mainActivity.findViewById(R.id.progress).getVisibility(), View.VISIBLE);
    }


}
