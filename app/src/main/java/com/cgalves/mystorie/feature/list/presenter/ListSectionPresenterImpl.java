package com.cgalves.mystorie.feature.list.presenter;

import com.cgalves.mystorie.common.abstractcalls.ListSectionAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.feature.home.model.Section;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by scopus on 25/07/18.
 */


@EBean
public class ListSectionPresenterImpl<V extends ListSectionContract.ListSectioPresenterView> extends BasePresenter<V> implements ListSectionContract.ListSectioPresenter<V> {
    ListSectionAbstractCall listSectionAbstractCall;

    @AfterInject
    void init() {
        listSectionAbstractCall = APIAbstractFactory.getFactory(context).getListSectionCall(busProvider.bus(), context);
    }

    @Override
    public void findSectionChoice(Section section) {
        listSectionAbstractCall.findSection(section);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(List<DetailSection> list) {
        getMvpView().onResultSectionChoice(list);
    }
}
