package com.cgalves.mystorie.feature.contact.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.fragment.BaseFragment;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.feature.admin.contact.ContactEditActivity_;
import com.cgalves.mystorie.feature.contact.presenter.ContactContract;
import com.cgalves.mystorie.feature.contact.presenter.ContactPresenterImpl;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by scopus on 27/07/18.
 */

@EFragment
public class ContactFragment extends BaseFragment implements ContactContract.ContactPresenterView {
    @Bean
    ContactPresenterImpl<ContactFragment> presenter;

    @ViewById
    LinearLayout toolbarFragment;

    @ViewById
    TextView tvTitle;

    @FragmentArg
    boolean showToolBarBack = false;

    @ViewById
    ImageView ivProfile;

    @ViewById
    TextView tvEmailContact, tvFacebookUrl, tvTwitterUrl, tvCellphone;

    @Click(R.id.btn_back)
    void onClickBack() {
        getActivity().finish();
    }

    @Click(R.id.fab)
    void onClickEditContact() {
        ContactEditActivity_.intent(this).start();
    }

    @AfterInject
    void calledAfterInjection() {
        presenter.attachView(this);
        presenter.register();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if (menuVisible) {
            if (verifiyIfPresenterIsNotNull()) {
                presenter.findInformationContact();
            }
        } else {
            if (verifiyIfPresenterIsNotNull()) {
                presenter.detachView();
                presenter.unregister();
            }
        }
    }

    private boolean verifiyIfPresenterIsNotNull() {
        return presenter != null;
    }

    @AfterViews
    void init() {
        showToolbarOrNot(showToolBarBack, toolbarFragment);
        tvTitle.setText(getString(R.string.contato));

        presenter.attachView(this);
        presenter.register();
        presenter.findInformationContact();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(ContactFragment.class.getName(), "onPause");
        if (verifiyIfPresenterIsNotNull()) {
            presenter.detachView();
            presenter.unregister();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);
        return view;
    }

    @Override
    public void onResultContact(User user) {
        tvEmailContact.setText("Email de contato: " + user.getEmail());
        tvFacebookUrl.setText(user.getFacebook());
        tvTwitterUrl.setText(user.getTwitter());
        tvCellphone.setText("Telefone: " + user.getCellphone());
        Glide.with(this).load(user.getImage()).into(ivProfile);
    }
}
