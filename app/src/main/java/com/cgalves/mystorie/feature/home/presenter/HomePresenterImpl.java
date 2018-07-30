package com.cgalves.mystorie.feature.home.presenter;

import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.feature.home.model.ImageResponse;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl;
import com.cgalves.mystorie.MyStorieApplication;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Scopus on 17/07/18.
 */

@EBean
public class HomePresenterImpl<V extends HomeContract.HomePresenterView> extends BasePresenter<V> implements HomeContract.HomePresenter<V> {


    @App
    MyStorieApplication application;

    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    HomeAbstractCall homeAbstractCall;

    @AfterInject
    void init() {
        homeAbstractCall = APIAbstractFactory.getFactory(context).getHomeCall(busProvider.bus(), context);
    }

    // call top header
    @Override
    public void findImagesTopHeader() {
        String token = application.getToken();
        homeAbstractCall.findImageTopHeader(token);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(ImageResponse imageResponse) {
        getMvpView().onResultImages(imageResponse.getList());
    }

    // end call top header
    @Override
    public void findSectionInBody() {
        String token = application.getToken();
        homeAbstractCall.findSectionsInBody(token);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSectionBodyLoaded(List<Section> list) {
        getMvpView().onResultSectionBody(list);
    }
}
