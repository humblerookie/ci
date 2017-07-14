package com.hr.ci.home.presenters;

import com.hr.ci.commons.model.Article;
import com.hr.ci.commons.model.ErrorResponse;
import com.hr.ci.commons.presenters.BasePresenter;
import com.hr.ci.commons.util.Constants;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.home.views.MainView;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {


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
            getView().toggleError(false, null);
        }

        interactor.fetchArticles(getFetchArticlesObservable(), getFetchArticlesObserver());
    }

    private Observable<String> getFetchArticlesObservable() {
        return Observable.just(Constants.NEWS_SRC.TECHCRUNCH);
    }

    private Observer<List<Article>> getFetchArticlesObserver() {
        return new Observer<List<Article>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Article> value) {
                Timber.d("Got articles %d", value.size());
                onArticleFetchSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e);
                onArticleFetchFailure(getNetworkError(e));
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void onArticleFetchSuccess(List<Article> articles) {
        if (isViewAttached()) {
            getView().toggleProgress(false);
            getView().showData(articles);
        }
    }

    @Override
    public void onArticleFetchFailure(ErrorResponse errorResponse) {
        if (isViewAttached()) {
            getView().toggleProgress(false);
            getView().toggleError(true, errorResponse.getMessage());
        }

    }

    public void setInteractor(MainInteractor interactor) {
        this.interactor = interactor;

    }

    public boolean isViewAttached() {
        return getView() != null && getView().isAvailable();
    }

}
