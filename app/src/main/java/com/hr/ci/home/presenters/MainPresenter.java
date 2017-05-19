package com.hr.ci.home.presenters;

import com.hr.ci.commons.model.Article;
import com.hr.ci.commons.presenters.BasePresenter;
import com.hr.ci.home.views.MainView;

import java.util.List;

public interface MainPresenter extends BasePresenter<MainView> {

    void fetchArticles();

    void onArticleFetchSuccess(List<Article> articles);

    void onArticleFetchFailure(String error);
}
