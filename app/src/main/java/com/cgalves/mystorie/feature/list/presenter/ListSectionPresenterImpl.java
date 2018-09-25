package com.cgalves.mystorie.feature.list.presenter;

import android.util.Log;

import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.ListSectionAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.common.presenter.BaseContract;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.feature.admin.contact.ContactActivity_;
import com.cgalves.mystorie.feature.home.model.Section;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Carlos Nicolau Galves on 25/07/18.
 */


@EBean
public class ListSectionPresenterImpl<V extends ListSectionContract.ListSectioPresenterView & BaseContract.View> extends BasePresenter<V> implements ListSectionContract.ListSectioPresenter<V> {
    ListSectionAbstractCall listSectionAbstractCall;

    @AfterInject
    void init() {
        listSectionAbstractCall = APIAbstractFactory.getFactory(context).getListSectionCall(bus.bus(), context);
    }

    @Override
    public void findSectionChoice(Section section) {
        listSectionAbstractCall.findSection(section);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(List<DetailSection> list) {
        getMvpView().onResultSectionChoice(list);
    }

    @Override
    public void onPresenterDestroy() {
        super.onPresenterDestroy();
        Log.e("onPresenterDestroy ", "onPresenterDestroy");
    }
}
