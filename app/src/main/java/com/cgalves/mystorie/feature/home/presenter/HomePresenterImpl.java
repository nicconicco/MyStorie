package com.cgalves.mystorie.feature.home.presenter;

import android.util.Log;

import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.presenter.BaseContract;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.feature.home.model.ImageResponse;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl;
import com.cgalves.mystorie.MyStorieApplication;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Carlos Nicolau Galves on 17/07/18.
 */

@EBean
public class HomePresenterImpl<V extends HomeContract.HomePresenterView & BaseContract.View> extends BasePresenter<V> implements HomeContract.HomePresenter<V> {

    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    HomeAbstractCall homeAbstractCall;

    @AfterInject
    void init() {
        homeAbstractCall = APIAbstractFactory.getFactory(context).getHomeCall(bus.bus(), context);
    }


    // call top header
    @Override
    public void findImagesTopHeader() {
        String token = MyStorieApplication.getsInstance().getToken();
        homeAbstractCall.findImageTopHeader(token);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(ImageResponse imageResponse) {
        getMvpView().onResultImages(imageResponse.getList());
    }

    // end call top header

    @Override
    public void findSectionInBody() {
        String token = MyStorieApplication.getsInstance().getToken();
        homeAbstractCall.findSectionsInBody(token);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSectionBodyLoaded(List<Section> list) {
        getMvpView().onResultSectionBody(list);
    }

    @Override
    public void onPresenterDestroy() {
        super.onPresenterDestroy();
        Log.e("onPresenterDestroy ", "onPresenterDestroy");
    }
}
