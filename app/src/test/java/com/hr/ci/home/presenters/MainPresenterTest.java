package com.hr.ci.home.presenters;


import com.hr.ci.commons.model.Article;
import com.hr.ci.home.interactor.MainInteractor;
import com.hr.ci.home.views.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class MainPresenterTest {


    @Mock
    MainView view;

    @Mock
    MainInteractor interactor;

    MainPresenter mainPresenter;


    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
        MainPresenterImpl mainPresenterImpl = new MainPresenterImpl(view);
        mainPresenterImpl.setInteractor(interactor);
        mainPresenter = mainPresenterImpl;
    }

    /*@Test
    public void verifyFetchingData_initiatesProgressDisplayAndInvokesInteractor() {
        Mockito.when(view.isAvailable()).thenReturn(true);
        mainPresenter.fetchArticles();
        verify(view).isAvailable();
        verify(view).toggleProgress(true);
        verify(interactor).fetchArticles("techcrunch");
    }*/

    @Test
    public void verifyOnSuccess_dataIsShownInView() {
        Mockito.when(view.isAvailable()).thenReturn(true);
        List<Article> articles = new ArrayList<>();
        mainPresenter.onArticleFetchSuccess(articles);
        verify(view).isAvailable();
        verify(view).toggleProgress(false);
        verify(view).showData(eq(articles));
    }

    /*@Test
    public void verifyOnFailure_errorIsShownInView() {
        Mockito.when(view.isAvailable()).thenReturn(true);
        mainPresenter.onArticleFetchFailure(Constants.ERROR.NETWORK_ERROR);
        verify(view).isAvailable();
        verify(view).toggleProgress(false);
        verify(view).toggleError(true, StringUtil.getResourceId(Constants.ERROR.NETWORK_ERROR));
    }*/


}