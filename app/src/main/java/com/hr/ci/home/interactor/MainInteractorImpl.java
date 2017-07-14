package com.hr.ci.home.interactor;

import com.hr.ci.commons.interactors.BaseInteractor;
import com.hr.ci.commons.model.Article;
import com.hr.ci.home.api.NewsService;
import com.hr.ci.home.presenters.MainPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;

public class MainInteractorImpl extends BaseInteractor implements MainInteractor {

    final MainPresenter mainPresenter;

    final NewsService newsService;

    final com.hr.ci.commons.util.Schedulers schedulers;


    public MainInteractorImpl(MainPresenter mainPresenter, NewsService newsService, com.hr.ci.commons.util.Schedulers schedulers) {
        this.mainPresenter = mainPresenter;
        this.newsService = newsService;
        this.schedulers = schedulers;

    }

    @Override
    public void fetchArticles(Observable<String> observable, Observer<List<Article>> subscriber) {
        observable
                .observeOn(Schedulers.io())
                .flatMap(source -> newsService.listArticles(source))
                .map(newsFeedResponse -> newsFeedResponse.getArticles())
                .subscribeOn(schedulers.ui())
                .subscribeWith(subscriber);
    }
}
