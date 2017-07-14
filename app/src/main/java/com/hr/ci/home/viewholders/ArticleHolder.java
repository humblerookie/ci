package com.hr.ci.home.viewholders;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hr.ci.R;
import com.hr.ci.commons.util.ImageLoader;
import com.hr.ci.commons.model.Article;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticleHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image)
    AppCompatImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.body)
    TextView body;


    Article data;
    ImageLoader imageLoader;

    public ArticleHolder(View itemView, ImageLoader imageLoader) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.imageLoader = imageLoader;
    }

    public void setData(Article data) {
        this.data = data;
        imageLoader.loadImage(data.getUrlToImage(), image, (Activity) image.getContext());
        title.setText(data.getTitle());
        author.setText(data.getAuthor());
        date.setText(data.getPublishedAtVerbose());
        body.setText(data.getDescription());
    }
}
