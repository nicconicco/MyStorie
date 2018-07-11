package com.mystorie.cgalves.mystorie.feature.view.login.presenter;

import com.mystorie.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.mystorie.cgalves.mystorie.common.presenter.BasePresenter;
import com.mystorie.cgalves.mystorie.model.factory.LoginAbstractCall;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Scopus on 11/07/18.
 */

@EBean
public class LoginPresenterImpl <V extends LoginPresenterView> extends BasePresenter<V> implements LoginPresenter<V> {

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

}
