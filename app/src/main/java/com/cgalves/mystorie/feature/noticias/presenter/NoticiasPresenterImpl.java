package com.cgalves.mystorie.feature.noticias.presenter;

import com.cgalves.mystorie.common.abstractcalls.NoticiasAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.feature.home.model.ImageResponse;
import com.cgalves.mystorie.feature.noticias.model.NoticiasResponseList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by scopus on 27/07/18.
 */

@EBean
public class NoticiasPresenterImpl<V extends NoticiasContract.NoticiasPresenterView> extends BasePresenter<V> implements NoticiasContract.NoticiasPresenter<V> {

    NoticiasAbstractCall noticiasAbstractCall;

    @AfterInject
    void inject() {
        noticiasAbstractCall = APIAbstractFactory.getFactory(context).getNoticiasCall(busProvider.bus(), context);
    }

    @Override
    public void findSectionNoticias() {
            noticiasAbstractCall.findNoticias("Notícias");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(NoticiasResponseList noticiasResponseList) {
        getMvpView().onResulSectiontNoticias(noticiasResponseList);
    }
}
