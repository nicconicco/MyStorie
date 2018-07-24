package com.cgalves.mystorie.feature.login.view;

import android.util.Log;
import android.widget.EditText;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.feature.login.presenter.LoginContract;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.cgalves.mystorie.common.utils.AlertDialogUtils.showAlertWarning;


@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements LoginContract.LoginPresenterView {

    @Bean
    LoginPresenterImpl<RegisterActivity> presenter;
    @ViewById
    EditText etUsername, etEmail, etPassword, etPasswordTwo;

    @AfterViews
    void init() {
        setupToolbar();
        toolbar.setTitle("Register");
        presenter.attachView(this);
        presenter.register();
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.state_after_view));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.detachView();
    }

    @Click(R.id.btn_registration)
    void onClickRegistration() {
        presenter.doRegistration(etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString());
    }

    @Override
    public void onLoginResult(Boolean isAdmin) {

    }

    @Override
    public void onResultRegistration(ParseUser result) {
        showAlertWarning(RegisterActivity.this, "Wellcome " + etUsername.getText().toString());
    }
}
