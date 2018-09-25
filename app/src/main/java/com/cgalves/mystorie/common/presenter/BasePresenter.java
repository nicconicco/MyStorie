package com.cgalves.mystorie.common.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.os.Bundle;

import com.cgalves.mystorie.common.providers.BusProvider;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;


/**
 * Created by CARLOS on 09/03/2018.
 */

@EBean
public class BasePresenter<V extends BaseContract.View> implements LifecycleObserver, BaseContract.Presenter<V>  {

    private Bundle stateBundle;

    @RootContext
    protected Context context;

    @Bean
    protected BusProvider bus;

    private V mMvpView;

    @Override
    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public V getView() {
        return mMvpView;
    }

    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    final public Bundle getStateBundle() {
        return stateBundle == null ?
                stateBundle = new Bundle() : stateBundle;
    }

    @Override
    public void onPresenterDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }

    @Override
    public void register() {
        if (!bus.bus().isRegistered(this)) {
            bus.bus().register(this);
        }
    }

    @Override
    public void unregister() {
        if (bus.bus().isRegistered(this)) {
            bus.bus().unregister(this);
        }
    }

    protected V getMvpView() {
        return mMvpView;
    }
}