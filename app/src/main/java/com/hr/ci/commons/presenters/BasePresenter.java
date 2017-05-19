package com.hr.ci.commons.presenters;

import com.hr.ci.commons.views.BaseView;

public interface BasePresenter<T extends BaseView> {

    T getView();
}
