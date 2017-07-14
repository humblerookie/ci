package com.hr.ci.home;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.bumptech.glide.request.ResourceCallback;
import com.hr.ci.R;
import com.hr.ci.commons.injectors.NetworkModuleInjector;
import com.hr.ci.home.activities.MainTestActivity;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.hr.ci.util.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    MockWebServer server;

    @Rule
    public ActivityTestRule<MainTestActivity> activityTestRule = new ActivityTestRule<>(MainTestActivity.class, true, false);


    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        NetworkModuleInjector.setBaseUrl(server.url("/").toString());
    }


    @Test
    public void initViewsDisplayed() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        Espresso.registerIdlingResources(new MainApiRequestIdlingResource(activityTestRule.getActivity()).setAlwaysIdle(true));
        onView(withId(R.id.progress)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.error_text)).check(matches(not(isDisplayed())));
    }

    @Test
    public void onApiError_errorShown() throws Exception {
        setUp();
        server.enqueue(new MockResponse().setResponseCode(401).setBody("{\"status\":\"404\",\"message\":\"Kya kiya tune\"}"));
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        Espresso.registerIdlingResources(new MainApiRequestIdlingResource(activityTestRule.getActivity()));
        onView(withId(R.id.error_text)).check(matches(withText("Kya kiya tune")));
        tearDown();
    }

    @Test
    public void onApiCalled_properResultShown() throws Exception {
        setUp();
        server.enqueue(new MockResponse().setResponseCode(200).setBody("{\"status\":\"ok\",\"source\":\"techcrunch\",\"sortBy\":\"top\",\"articles\":[{\"author\":\"Sarah Buhr\",\"title\":\"Comma.ai launches an $88 universal car interface called Panda\",\"description\":\"George Hotz is fidgeting in his living room, wearing dark shades and a giant comma on his t-shirt as he shows me his company's latest product, an $88..\",\"url\":\"https://techcrunch.com/2017/07/07/comma-ai-launches-an-88-universal-car-interface-called-panda/\",\"urlToImage\":\"https://img.vidible.tv/prod/2017-07/07/595ef480f3bdc95fde23abee/595ef4e9e9399f7778c57e97_o_U_v1.jpg?w=764&h=400\",\"publishedAt\":\"2017-07-07T13:00:15Z\"},{\"author\":\"Pascal Levensohn, Andrew Krowne\",\"title\":\"Why SAFE notes are not safe for entrepreneurs\",\"description\":\"The shortcomings of SAFE notes are coming home to roost; ironically, entrepreneurs are paying the price. Y Combinator invented the notes with a noble goal:..\",\"url\":\"https://techcrunch.com/2017/07/08/why-safe-notes-are-not-safe-for-entrepreneurs/\",\"urlToImage\":\"https://tctechcrunch2011.files.wordpress.com/2017/07/safewall.jpg?w=764&h=400&crop=1\",\"publishedAt\":\"2017-07-08T18:00:56Z\"},{\"author\":\"Joanna Glasner\",\"title\":\"These South Bay and Peninsula cities are raking in venture capital\",\"description\":\"Though the region known as Silicon Valley covers dozens of municipalities, a few cities get outsized attention in tech circles. Everyone knows Palo Alto,..\",\"url\":\"https://techcrunch.com/2017/07/08/these-south-bay-and-peninsula-cities-are-raking-in-venture-capital/\",\"urlToImage\":\"https://tctechcrunch2011.files.wordpress.com/2017/07/bay-bridgetc.gif?w=673&h=379&crop=1\",\"publishedAt\":\"2017-07-08T16:00:14Z\"},{\"author\":\"Brian Heater\",\"title\":\"Google is funding the creation of software that writes local news stories\",\"description\":\"Google’s Digital News Initiative has committed £622,000 ($805,000) to fund an automated news writing initiative for UK-based news agency, The Press..\",\"url\":\"https://techcrunch.com/2017/07/08/google-is-funding-the-creation-of-software-that-writes-local-news-stories/\",\"urlToImage\":\"https://tctechcrunch2011.files.wordpress.com/2015/02/newspapers.jpg?w=764&h=400&crop=1\",\"publishedAt\":\"2017-07-08T15:50:48Z\"},{\"author\":\"Katie Roof\",\"title\":\"Amazon wasn’t the only company that tried to buy Whole Foods\",\"description\":\"A new filing confirms that there were other suitors trying to buy Whole Foods, but that Amazon put pressure on Whole Foods not to talk to them. The document..\",\"url\":\"https://techcrunch.com/2017/07/07/amazon-wasnt-the-only-company-that-tried-to-buy-whole-foods/\",\"urlToImage\":\"https://tctechcrunch2011.files.wordpress.com/2017/06/amazon-whole-foods-banner.jpg?w=764&h=400&crop=1\",\"publishedAt\":\"2017-07-07T21:50:44Z\"}]}\n"));
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
        Espresso.registerIdlingResources(new MainApiRequestIdlingResource(activityTestRule.getActivity()));
        onView(withRecyclerView(R.id.list).atPosition(0)).check(matches(hasDescendant(withText("Comma.ai launches an $88 universal car interface called Panda"))));
        tearDown();
    }


    public void tearDown() throws Exception {
        server.shutdown();
    }


    private class MainApiRequestIdlingResource implements IdlingResource {
        private MainTestActivity mainTestActivity;
        private ResourceCallback resourceCallback;
        private boolean isAlwaysIdle;

        public MainApiRequestIdlingResource(MainTestActivity mainTestActivity) {
            this.mainTestActivity = mainTestActivity;
        }

        @Override
        public String getName() {
            return MainApiRequestIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            boolean idle = !isAlwaysIdle ? mainTestActivity.isIdle() : true;
            if (idle) {
                resourceCallback.onTransitionToIdle();
            }
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }

        public MainApiRequestIdlingResource setAlwaysIdle(boolean alwaysIdle) {
            isAlwaysIdle = alwaysIdle;
            return this;
        }
    }


}

