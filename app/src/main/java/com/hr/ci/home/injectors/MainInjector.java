package com.hr.ci.home.injectors;

import com.hr.ci.commons.injectors.NetworkModuleInjector;
import com.hr.ci.commons.injectors.SchedulerInjector;
import com.hr.ci.home.api.NewsService;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.home.interactor.MainInteractorImpl;
import com.hr.ci.home.presenters.MainPresenter;
import com.hr.ci.home.presenters.MainPresenterImpl;
import com.hr.ci.home.views.MainView;

public class MainInjector {

    public MainPresenter getMainPresenter(MainView mainView) {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(mainView);
        mainPresenter.setInteractor(getMainInteractor(mainPresenter));
        return mainPresenter;
    }

    public MainInteractor getMainInteractor(MainPresenter mainPresenter) {
        MainInteractorImpl mainInteractor = new MainInteractorImpl(mainPresenter,
                NetworkModuleInjector.getRetrofit(NetworkModuleInjector.getOkHttpClient()).create(NewsService.class)
                , SchedulerInjector.getSchedulers()
        );
        return mainInteractor;
    }
}
