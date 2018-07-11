package com.mystorie.cgalves.mystorie.feature.view.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.mystorie.cgalves.mystorie.R;
import com.mystorie.cgalves.mystorie.common.activity.BaseActivity;
import com.mystorie.cgalves.mystorie.feature.view.login.presenter.LoginPresenterImpl;
import com.mystorie.cgalves.mystorie.feature.view.login.presenter.LoginPresenterView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginPresenterView {

    @Bean
    LoginPresenterImpl<LoginActivity> presenter;

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
    EditText etUserName;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @Click(R.id.btn_login)
    void onClickLogin() {
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        presenter.doLogin(username, password);
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : doLogin");
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickLogin");
    }

    @Click(R.id.btn_facebook)
    void onClickFacebook(){
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickFacebook");
    }

    @Click(R.id.btn_twitter)
    void onClickTwitter(){
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickTwitter");
    }

    @Override
    public void onLoginResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
