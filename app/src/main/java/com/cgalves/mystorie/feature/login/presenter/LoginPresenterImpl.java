package com.cgalves.mystorie.feature.login.presenter;

import android.util.Log;

import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.MyStorieApplication;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Scopus on 11/07/18.
 */

@EBean
public class LoginPresenterImpl<V extends LoginContract.LoginPresenterView> extends BasePresenter<V> implements LoginContract.LoginPresenter<V> {

    @App
    MyStorieApplication application;

    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    LoginAbstractCall loginAbstractCall;

    @AfterInject
    void init() {
        loginAbstractCall = APIAbstractFactory.getFactory(context).getLoginCall(busProvider.bus(), context);
    }

    // Call Login
    @Override
    public void doLogin(String username, String password) {
        loginAbstractCall.login(username, password);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLoginResultCall(ParseUser resultLogin) {
        setToken(resultLogin);
        getMvpView().onLoginResult(isAdmin(resultLogin));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onError(String error) {
        getMvpView().onError(error);
    }

    @Override
    public void doRegistration(String username, String password, String email) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setEmail(password);
        user.setPassword(email);
        user.signUpInBackground(e -> {
            try {
                if (e == null) {
                    ParseUser result = ParseUser.getCurrentUser();
                    setUserInformation(result);
                    getMvpView().onResultRegistration(ParseUser.getCurrentUser());
                } else {
                    ParseUser.logOut();
                    getMvpView().onError(e.getMessage());
                }
            } catch (Exception error) {
                Log.e(TAG, error.getMessage());
                getMvpView().onError(error.getMessage());
            }
        });
    }

    private void setUserInformation(ParseUser result) {
        setToken(result);
        getMvpView().onLoginResult(isAdmin(result));
    }

    private void setToken(ParseUser result) {
        application.setToken(result.getSessionToken());
        application.setName(result.getUsername());
    }

    private boolean isAdmin(ParseUser result) {
        if(result != null) {
            if(result.get("admin") != null) {
                return (boolean) result.get("admin");
            }
        }

        return false;
    }
}
