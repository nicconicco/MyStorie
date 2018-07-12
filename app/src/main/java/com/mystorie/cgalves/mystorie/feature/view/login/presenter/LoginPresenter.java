package com.mystorie.cgalves.mystorie.feature.view.login.presenter;

import android.content.Context;

import com.mystorie.cgalves.mystorie.common.presenter.MvpPresenter;
import com.mystorie.cgalves.mystorie.common.presenter.MvpView;

/**
 * Created by Scopus on 11/07/18.
 */

public interface LoginPresenter <V extends LoginPresenterView & MvpView> extends MvpPresenter<V> {
    void doLogin(String username, String password);
    void onClickRegister(Context context);
    void doRegistration(String username, String password, String email);
}
