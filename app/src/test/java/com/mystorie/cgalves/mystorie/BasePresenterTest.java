package com.mystorie.cgalves.mystorie;

import android.support.annotation.CallSuper;

import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.common.presenter.MvpView;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import io.reactivex.schedulers.TestScheduler;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by scopus on 03/08/18.
 */

public abstract class BasePresenterTest<P extends BasePresenter, V extends MvpView> {

    @Mock
    public V view;
    public P presenter;
    public TestScheduler testScheduler;
    @CallSuper
    @Before
    public void before() {
        initMocks(this);
        testScheduler = new TestScheduler();

        view = createView();
        presenter = createPresenter();
        presenter.attachView(view);
    }
    @CallSuper @After
    public void tearDown() {
        presenter.detachView();
    }
    abstract P createPresenter();
    abstract V createView();
}
