package com.hr.ci.commons.model;


import com.squareup.moshi.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import timber.log.Timber;


public class Article {

    private static final SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    private static final SimpleDateFormat to = new SimpleDateFormat("dd MMM yyyy 'at' hh:mm", Locale.getDefault());
    @Json(name = "author")
    private String author;
    @Json(name = "title")
    private String title;
    @Json(name = "description")
    private String description;
    @Json(name = "url")
    private String url;
    @Json(name = "urlToImage")
    private String urlToImage;
    @Json(name = "publishedAt")
    private String publishedAt;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getPublishedAtVerbose() {
        try {
            return to.format(from.parse(publishedAt));
        } catch (ParseException e) {
            Timber.e(e);
        }
        return null;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

}