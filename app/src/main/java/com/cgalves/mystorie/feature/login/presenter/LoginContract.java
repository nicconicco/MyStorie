package com.cgalves.mystorie.feature.login.presenter;

import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.presenter.BaseContract;
import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;

/**
 * Created by Carlos Nicolau Galves on 17/07/18.
 */

public class LoginContract {

    public interface LoginPresenter<V extends LoginPresenterView & BaseContract.View> extends MvpPresenter<V> {
        void doLogin(String username, String password);
        void doRegistration(String username, String password, String email);
    }

    public interface LoginPresenterView extends MvpView {
        void onLoginResult(Boolean isAdmin);
        void onResultRegistration(User result);
    }
}
