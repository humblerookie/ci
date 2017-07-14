package com.hr.ci.home.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hr.ci.R;
import com.hr.ci.commons.activities.BaseActivity;
import com.hr.ci.commons.model.Article;
import com.hr.ci.commons.util.ImageLoaderImpl;
import com.hr.ci.home.adapters.NewsFeedAdapter;
import com.hr.ci.home.injectors.MainInjector;
import com.hr.ci.home.presenters.MainPresenter;
import com.hr.ci.home.views.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.error_text)
    TextView errorText;

    NewsFeedAdapter adapter;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        init();
        retrieveData();
    }

    private void init() {
        presenter = new MainInjector().getMainPresenter(this);
        adapter = new NewsFeedAdapter(new ArrayList<Article>(), new ImageLoaderImpl());
        setSupportActionBar(toolbar);
        list.setAdapter(adapter);
    }

    private void retrieveData() {
        presenter.fetchArticles();
    }


    @Override
    public boolean isAvailable() {
        return isAlive();
    }

    @Override
    public void toggleProgress(boolean makeVisible) {
        progress.setVisibility(makeVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showData(List<Article> articles) {
        adapter.setData(articles);

    }

    @Override
    public void toggleError(boolean makeVisible, String stringRes) {
        errorText.setVisibility(makeVisible ? View.VISIBLE : View.GONE);
        errorText.setText(stringRes);
    }
}
