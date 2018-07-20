package com.cgalves.mystorie.feature.login.presenter;

import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;
import com.parse.ParseUser;

/**
 * Created by Scopus on 17/07/18.
 */

public class LoginContract {

    public interface LoginPresenter <V extends LoginPresenterView & MvpView> extends MvpPresenter<V> {
        void doLogin(String username, String password);
        void doRegistration(String username, String password, String email);
    }

    public interface LoginPresenterView extends MvpView {
        void onLoginResult(ParseUser result);
    }
}
