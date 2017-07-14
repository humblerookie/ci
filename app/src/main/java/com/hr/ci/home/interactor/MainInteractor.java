package com.hr.ci.home.interactor;

import com.hr.ci.commons.model.Article;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface MainInteractor {

    void fetchArticles(Observable<String> observable, Observer<List<Article>> subscriber);
}
