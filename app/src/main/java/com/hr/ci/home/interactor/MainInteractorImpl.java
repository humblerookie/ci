package com.hr.ci.home.interactor;

import android.util.Log;

import com.hr.ci.commons.model.NewsFeed;
import com.hr.ci.commons.util.Constants;
import com.hr.ci.home.api.NewsService;
import com.hr.ci.home.presenters.MainPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor {

    MainPresenter mainPresenter;
    private static final String TAG = "MainInteractorImpl";

    final NewsService newsService;

    public MainInteractorImpl(MainPresenter mainPresenter, NewsService newsService) {
        this.mainPresenter = mainPresenter;
        this.newsService = newsService;
    }

    @Override
    public void fetchArticles(String source) {
        Call<NewsFeed> call = newsService.listArticles(source);
        call.enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(Call<NewsFeed> call, Response<NewsFeed> response) {
                mainPresenter.onArticleFetchSuccess(response.body().getArticles());

            }

            @Override
            public void onFailure(Call<NewsFeed> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                mainPresenter.onArticleFetchFailure(t instanceof IOException ? Constants.ERROR.NETWORK_ERROR : Constants.ERROR.SERVER_ERROR);
            }
        });
    }
}
