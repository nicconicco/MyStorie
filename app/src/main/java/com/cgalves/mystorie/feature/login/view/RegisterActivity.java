package com.cgalves.mystorie.feature.login.view;

import android.util.Log;
import android.widget.EditText;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.cgalves.mystorie.common.utils.AlertDialogUtils.showAlertWarning;


@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements LoginPresenterView {

    @Bean
    LoginPresenterImpl<RegisterActivity> presenter;

    @AfterViews
    void init(){
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
    @ViewById(R.id.et_username)
    EditText etUsername;

    @ViewById(R.id.et_email)
    EditText etEmail;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @ViewById(R.id.et_password_two)
    EditText etPasswordTwo;

    @Click(R.id.btn_registration)
    void onClickRegistration(){
       presenter.doRegistration(etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString());
    }

    @Override
    public void onLoginResult(String result) {
        showAlertWarning(RegisterActivity.this, "Wellcome "+etUsername.getText().toString());
    }
}
