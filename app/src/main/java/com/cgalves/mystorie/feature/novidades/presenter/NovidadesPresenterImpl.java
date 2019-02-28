package com.cgalves.mystorie.feature.novidades.presenter;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.NovidadesAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.feature.novidades.model.NovidadesResponseList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by scopus on 27/07/18.
 */

@EBean
public class NovidadesPresenterImpl<V extends NovidadesContract.NovidadesPresenterView> extends BasePresenter<V> implements NovidadesContract.NovidadesPresenter<V> {

    NovidadesAbstractCall novidadesAbstractCall;

    public NovidadesPresenterImpl(Context context) {
        super(context);
    }

    @AfterInject
    void inject() {
        novidadesAbstractCall = APIAbstractFactory.getFactory(context).getNovidadesCall(busProvider.bus(), context);
    }

    @Override
    public void findSectionNovidades() {
        novidadesAbstractCall.findNovidades("Novidades");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(NovidadesResponseList noticiasResponseList) {
        getMvpView().onResulSectiontNovidades(noticiasResponseList);
    }

    @Override
    protected void attachRepositories() {

    }

    @Override
    protected void detachRepositories() {

    }
}
