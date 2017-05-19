package com.hr.ci.commons.model;

import com.squareup.moshi.Json;

import java.util.List;

public class NewsFeed {

    @Json(name = "status")
    private String status;
    @Json(name = "source")
    private String source;
    @Json(name = "sortBy")
    private String sortBy;
    @Json(name = "articles")
    private List<Article> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}