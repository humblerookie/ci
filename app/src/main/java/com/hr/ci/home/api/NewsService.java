package com.hr.ci.home.api;

import com.hr.ci.commons.model.NewsFeed;
import com.hr.ci.commons.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("articles?apiKey=" + Constants.KEY)
    Observable<NewsFeed> listArticles(@Query("source") String source);
}
