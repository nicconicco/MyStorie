package com.mystorie.cgalves.mystorie.feature.view.login.presenter;

import android.content.Context;
import android.util.Log;

import com.mystorie.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.mystorie.cgalves.mystorie.common.factory.LoginAbstractCall;
import com.mystorie.cgalves.mystorie.common.presenter.BasePresenter;
import com.mystorie.cgalves.mystorie.feature.view.login.view.RegisterActivity_;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Scopus on 11/07/18.
 */

@EBean
public class LoginPresenterImpl <V extends LoginPresenterView> extends BasePresenter<V> implements LoginPresenter<V> {

    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    LoginAbstractCall loginAbstractCall;

    @AfterInject
    void init() {
        loginAbstractCall = APIAbstractFactory.getFactory(context).getLoginCall(bus.bus(), context);
    }

    // Call Login
    @Override
    public void doLogin(String username, String password) {
        loginAbstractCall.login(username, password);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLoginResultCall(String resultLogin) {
        getMvpView().onLoginResult(resultLogin);
    }

    @Override
    public void onClickRegister(Context context) {
        RegisterActivity_.intent(context).start();
    }

    @Override
    public void doRegistration(String username, String password, String email) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setEmail(password);
        user.setPassword(email);
        user.signUpInBackground(e -> {
            try{
                if (e == null) {
                    getMvpView().onLoginResult(username);
                } else {
                    ParseUser.logOut();
                    getMvpView().onLoginResult(e.getMessage());
                }
            }catch (Exception error){
                Log.e(TAG, error.getMessage());
            }
        });
    }



}
