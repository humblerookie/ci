package com.hr.ci.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hr.ci.R;
import com.hr.ci.commons.ImageLoader;
import com.hr.ci.commons.model.Article;
import com.hr.ci.home.viewholders.ArticleHolder;

import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<ArticleHolder> {

    List<Article> articles;
    ImageLoader imageLoader;

    public NewsFeedAdapter(List<Article> articles, ImageLoader imageLoader) {
        this.articles = articles;
        this.imageLoader = imageLoader;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false), imageLoader);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        holder.setData(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setData(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
}
