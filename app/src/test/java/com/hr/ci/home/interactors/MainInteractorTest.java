package com.hr.ci.home.interactors;


import com.hr.ci.commons.injectors.SchedulerInjector;
import com.hr.ci.commons.model.Article;
import com.hr.ci.commons.model.NewsFeed;
import com.hr.ci.home.api.NewsService;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.home.interactor.MainInteractorImpl;
import com.hr.ci.home.presenters.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.TestObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class MainInteractorTest {


    MainInteractor interactor;

    @Mock
    MainPresenter mainPresenter;

    @Mock
    NewsService newsService;

    @Mock
    Observable<NewsFeed> newsFeedCall;

    @Mock
    Observer<List<Article>> observer;


    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
        interactor = new MainInteractorImpl(mainPresenter, newsService, SchedulerInjector.getSchedulers());
    }

    @Test
    public void verifyApiSuccess_resultsInSubscriberInvocation_whenInvoked() {
        String source = "techcrunch";
        Article article = new Article();
        article.setAuthor("Abc");
        article.setDescription("Abc");
        article.setPublishedAt("Abc");
        article.setTitle("Abc");
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.setArticles(articles);
        newsFeed.setSource(source);
        newsFeed.setStatus("all_cool");
        when(newsService.listArticles(any())).thenReturn(Observable.just(newsFeed));
        TestObserver<List<Article>> testSubscriber = TestObserver.create();
        interactor.fetchArticles(Observable.just(source), testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertValues(articles);

    }


    @Test
    public void verifyApiSuccess_resultsInSubscriberError_whenInvoked() {
        String source = "techcrunch";
        Article article = new Article();
        article.setAuthor("Abc");
        article.setDescription("Abc");
        article.setPublishedAt("Abc");
        article.setTitle("Abc");
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.setArticles(articles);
        newsFeed.setSource(source);
        newsFeed.setStatus("all_cool");
        when(newsService.listArticles(any())).thenReturn(Observable.just(newsFeed));
        TestObserver<List<Article>> testSubscriber = TestObserver.create();
        interactor.fetchArticles(Observable.just(source), testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertValues(articles);

    }

    @Test
    public void verifyFailureCase_whenInvoked() {
        String source = "techcrunch";
        Throwable throwable = new RuntimeException();
        when(newsService.listArticles(any())).thenThrow(throwable);
        TestObserver<List<Article>> testSubscriber = TestObserver.create();
        interactor.fetchArticles(Observable.just(source), testSubscriber);
        testSubscriber.assertNoValues();
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(throwable);
    }
}