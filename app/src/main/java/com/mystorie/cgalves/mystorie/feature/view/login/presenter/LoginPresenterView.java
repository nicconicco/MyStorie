package com.mystorie.cgalves.mystorie.feature.view.login.presenter;

import com.mystorie.cgalves.mystorie.common.presenter.MvpView;

/**
 * Created by Scopus on 11/07/18.
 */

public interface LoginPresenterView extends MvpView {
    void onLoginResult(String result);
}
