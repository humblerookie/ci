package com.hr.ci.home.api;

import com.hr.ci.commons.util.Constants;
import com.hr.ci.commons.model.NewsFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("articles?apiKey=" + Constants.KEY)
    Call<NewsFeed> listArticles(@Query("source") String source);
}
