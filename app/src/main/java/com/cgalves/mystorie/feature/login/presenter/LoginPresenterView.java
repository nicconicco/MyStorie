package com.cgalves.mystorie.feature.login.presenter;

import com.cgalves.mystorie.common.presenter.MvpView;

/**
 * Created by Scopus on 11/07/18.
 */

public interface LoginPresenterView extends MvpView {
    void onLoginResult(String result);
}
