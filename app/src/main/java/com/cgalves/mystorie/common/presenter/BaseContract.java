package com.cgalves.mystorie.common.presenter;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

/**
 * Created by Carlos Nicolau Galves on 25/09/18.
 */

public interface BaseContract {

    interface View {
    }

    interface Presenter<V extends BaseContract.View> {

        void attachLifecycle(Lifecycle lifecycle);

        Bundle getStateBundle();

        void detachLifecycle(Lifecycle lifecycle);

        void attachView(V view);

        void detachView();

        V getView();

        boolean isViewAttached();

        void onPresenterDestroy();

        void register();

        void unregister();
    }
}
