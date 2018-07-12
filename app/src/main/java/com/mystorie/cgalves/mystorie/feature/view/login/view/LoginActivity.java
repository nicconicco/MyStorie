package com.mystorie.cgalves.mystorie.feature.view.login.view;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mystorie.cgalves.mystorie.R;
import com.mystorie.cgalves.mystorie.common.activity.BaseActivity;
import com.mystorie.cgalves.mystorie.common.utils.KeyHashUtils;
import com.mystorie.cgalves.mystorie.feature.view.login.presenter.LoginPresenterImpl;
import com.mystorie.cgalves.mystorie.feature.view.login.presenter.LoginPresenterView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginPresenterView {

    @Bean
    LoginPresenterImpl<LoginActivity> presenter;
    private CallbackManager callbackManager;

    @AfterViews
    void init(){
        presenter.attachView(this);
        presenter.register();
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.state_after_view));

        callbackManager = CallbackManager.Factory.create();


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        KeyHashUtils.generateKeyHash(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
        presenter.doLogin(etUserName.getText().toString(), etPassword.getText().toString());
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

    @Click(R.id.btn_register)
    void onClickRegister(){
        presenter.onClickRegister(this);
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickRegister");
    }

    @Override
    public void onLoginResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
