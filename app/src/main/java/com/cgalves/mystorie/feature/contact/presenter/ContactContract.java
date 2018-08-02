package com.cgalves.mystorie.feature.contact.presenter;

import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.presenter.MvpPresenter;
import com.cgalves.mystorie.common.presenter.MvpView;

public class ContactContract {

    public interface ContactPresenter<V extends ContactPresenterView & MvpView> extends MvpPresenter<V> {
        void findInformationContact();
    }

    public interface ContactPresenterView extends MvpView {
        void onResultContact(User user);
    }
}
