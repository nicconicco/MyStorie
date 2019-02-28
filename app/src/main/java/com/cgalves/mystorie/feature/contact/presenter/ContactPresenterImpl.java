package com.cgalves.mystorie.feature.contact.presenter;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.ContactAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.presenter.BasePresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@EBean
public class ContactPresenterImpl<V extends ContactContract.ContactPresenterView> extends BasePresenter<V> implements ContactContract.ContactPresenter<V> {

    ContactAbstractCall contactAbstractCall;

    public ContactPresenterImpl(Context context) {
        super(context);
    }

    @AfterInject
    void init() {
        contactAbstractCall = APIAbstractFactory.getFactory(context).getContactCall(busProvider.bus(), context);
    }

    @Override
    public void findInformationContact() {
        contactAbstractCall.findInformationUser();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onResultContact(User user) {
        getMvpView().onResultContact(user);
    }

    @Override
    protected void attachRepositories() {

    }

    @Override
    protected void detachRepositories() {

    }
}
