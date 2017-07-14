package com.hr.ci.home.views;

import com.hr.ci.commons.model.Article;
import com.hr.ci.commons.views.BaseView;

import java.util.List;

public interface MainView extends BaseView {

    void toggleProgress(boolean makeVisible);

    void showData(List<Article> articles);

    void toggleError(boolean makeVisible, String error);
}
