package com.cgalves.mystorie.common.presenter;

/**
 * Created by Carlos Nicolau Galves on 09/03/2018.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

    void register();

    void unregister();
}