package com.hr.ci.home;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import com.hr.ci.R;
import com.hr.ci.home.activities.MainTestActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.hr.ci.util.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.notNullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainTestActivity> activityTestRule = new ActivityTestRule<>(MainTestActivity.class);

    private class MainApiRequestIdlingResource implements IdlingResource {
        private MainTestActivity mainTestActivity;
        private ResourceCallback resourceCallback;

        public MainApiRequestIdlingResource(MainTestActivity mainTestActivity) {
            this.mainTestActivity = mainTestActivity;
        }

        @Override
        public String getName() {
            return MainApiRequestIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            boolean idle = mainTestActivity.isIdle();
            if (idle) {
                resourceCallback.onTransitionToIdle();
            }
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }
    }


    private static Matcher<View> contains(final String expected) {
        return new TypeSafeMatcher<View>() {


            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View item) {
                return item instanceof TextView && ((TextView) item).getText().toString().contains(expected);
            }
        };
    }

    @Test
    public void onApiCalled_properResultShown() {
        Espresso.registerIdlingResources(new MainApiRequestIdlingResource(activityTestRule.getActivity()));
        try {
            onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.title)).check(matches(notNullValue()));
        } catch (Exception e) {
            onView(withId(R.id.error_text)).check(matches(isDisplayed()));
        }
    }

}

