package com.cgalves.mystorie.common.presenter;

import android.content.Context;

import com.cgalves.mystorie.common.providers.BusProvider;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;


/**
 * Created by Scopus on 09/03/2018.
 */

@EBean
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    @RootContext
    protected Context context;

    @Bean
    protected BusProvider bus;

    private V mMvpView;

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
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