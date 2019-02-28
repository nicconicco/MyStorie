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
public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    @RootContext
    protected Context context;

    @Bean
    protected BusProvider busProvider;

    private V mMvpView;

    public BasePresenter(Context context) {
        this.context = context;
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
    public void register() {
        if (!busProvider.bus().isRegistered(this)) {
            busProvider.bus().register(this);
        }
    }

    @Override
    public void unregister() {
        if (busProvider.bus().isRegistered(this)) {
            busProvider.bus().unregister(this);
        }
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void setView(V view) {
        this.mMvpView = view;
    }

    protected abstract void attachRepositories();

    protected abstract void detachRepositories();
}