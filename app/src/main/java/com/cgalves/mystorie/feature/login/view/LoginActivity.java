package com.cgalves.mystorie.feature.login.view;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.feature.admin.home.MasterHomeActivity_;
import com.cgalves.mystorie.feature.home.view.activity.HomeActivity_;
import com.cgalves.mystorie.feature.login.presenter.LoginContract;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_login_v2)
public class LoginActivity extends BaseActivity implements LoginContract.LoginPresenterView {

    @Bean
    LoginPresenterImpl<LoginActivity> presenter;

    @ViewById(R.id.et_username)
    EditText etUserName;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @AfterViews
    void init() {
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

    @Click(R.id.btn_login)
    void onClickLogin() {
        progressBar.show(getSupportFragmentManager(), "Whatever");
        presenter.doLogin(etUserName.getText().toString(), etPassword.getText().toString());
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : doLogin");
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickLogin");
    }

    @Override
    public void onLoginResult(boolean isAdmin) {
        progressBar.dismiss();

        if (isAdmin) {
            MasterHomeActivity_.intent(this).start();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else {
            HomeActivity_.intent(this).start();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    public void onError(String error) {
        progressBar.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResultRegistration(User result) {

    }
}
