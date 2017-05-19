package com.hr.ci.home.injectors;

import com.hr.ci.commons.model.Article;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.home.presenters.MainPresenter;
import com.hr.ci.home.views.MainView;

import java.util.List;

public class MainInjector {

    public MainPresenter getMainPresenter(MainView mainView) {
        return new MainPresenter() {
            @Override
            public void fetchArticles() {

            }

            @Override
            public void onArticleFetchSuccess(List<Article> articles) {

            }

            @Override
            public void onArticleFetchFailure(String error) {

            }

            @Override
            public MainView getView() {
                return null;
            }
        };
    }

    public MainInteractor getMainInteractor(MainPresenter mainPresenter) {

        return new MainInteractor() {
            @Override
            public void fetchArticles(String source) {

            }
        };
    }
}
