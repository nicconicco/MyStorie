package com.cgalves.mystorie.common.presenter;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Carlos Nicolau Galves on 25/09/18.
 */

public final class BaseViewModel<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends ViewModel {

    private P presenter;

    public void setPresenter(P presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    public P getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.onPresenterDestroy();
        presenter = null;
    }
}
