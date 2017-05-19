package com.hr.ci.home.presenters;

import com.hr.ci.commons.util.Constants;
import com.hr.ci.commons.util.StringUtil;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.commons.model.Article;
import com.hr.ci.home.views.MainView;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {


    WeakReference<MainView> viewWeakReference;

    MainInteractor interactor;

    public MainPresenterImpl(MainView mainView) {
        this.viewWeakReference = new WeakReference<>(mainView);
    }

    @Override
    public MainView getView() {
        return viewWeakReference.get();
    }

    @Override
    public void fetchArticles() {
        if (isViewAttached()) {
            getView().toggleProgress(true);
            getView().toggleError(false, 0);
        }
        interactor.fetchArticles(Constants.NEWS_SRC.TECHCRUNCH);
    }

    @Override
    public void onArticleFetchSuccess(List<Article> articles) {
        if (isViewAttached()) {
            getView().toggleProgress(false);
            getView().showData(articles);
        }
    }

    @Override
    public void onArticleFetchFailure(String error) {
        if (isViewAttached()) {
            getView().toggleProgress(false);
            getView().toggleError(true, StringUtil.getResourceId(error));
        }

    }

    public void setInteractor(MainInteractor interactor) {
        this.interactor = interactor;

    }

    public boolean isViewAttached() {
        return getView() != null && getView().isAvailable();
    }

}
