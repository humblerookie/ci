package com.hr.ci.home.interactors;


import com.hr.ci.home.api.NewsService;
import com.hr.ci.home.presenters.MainPresenter;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.home.interactor.MainInteractorImpl;
import com.hr.ci.commons.model.NewsFeed;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class MainInteractorTest {


    MainInteractor interactor;

    @Mock
    MainPresenter mainPresenter;

    @Mock
    NewsService newsService;

    @Mock
    Call<NewsFeed> newsFeedCall;

    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
        interactor = new MainInteractorImpl(mainPresenter, newsService);
    }

    @Test
    public void verifyMakesApicall_whenInvoked() {
        Mockito.when(newsService.listArticles("techcrunch")).thenReturn(newsFeedCall);
        interactor.fetchArticles("techcrunch");
        verify(newsService).listArticles("techcrunch");
        verify(newsFeedCall).enqueue(any(Callback.class));
    }
}