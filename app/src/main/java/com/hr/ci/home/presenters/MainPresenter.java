package com.hr.ci.home.presenters;

import com.hr.ci.commons.model.Article;
import com.hr.ci.commons.model.ErrorResponse;

import java.util.List;

public interface MainPresenter {

    void fetchArticles();

    void onArticleFetchSuccess(List<Article> articles);

    void onArticleFetchFailure(ErrorResponse error);
}
