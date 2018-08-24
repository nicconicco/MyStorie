package com.cgalves.mystorie.feature.login.view;

import android.content.Intent;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

import static com.google.android.gms.internal.zzdmy.checkNotNull;

@EActivity(R.layout.activity_login_v2)
public class LoginActivity extends BaseActivity implements LoginContract.LoginPresenterView {

    @Bean
    LoginPresenterImpl<LoginActivity> presenter;

    @ViewById(R.id.et_username)
    EditText etUserName;

    @ViewById(R.id.et_password)
    EditText etPassword;

    private CallbackManager callbackManager;

    @AfterViews
    void init() {
        presenter.attachView(this);
        presenter.register();
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.state_after_view));
//        checkNotNull(presenter);
//        facebookCalls();
//        KeyHashUtils.generateKeyHash(this);
    }

    private void facebookCalls() {
        callbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email");
//        // If using in a fragment
//
//        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d(getString(R.string.tag_next_flow), "facebook onActivityResult requestCode = " + requestCode + ", resultCode = " + resultCode + ", data =" + data);
        super.onActivityResult(requestCode, resultCode, data);
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

    void onClickFacebook() {
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickFacebook");
    }

//    @Click(R.id.btn_twitter)
//    void onClickTwitter(){
//        presenter.doLogin(etUserName.getText().toString(), etPassword.getText().toString());
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickTwitter");
//    }

    @Click(R.id.btn_register)
    void onClickRegister() {
        RegisterActivity_.intent(this).start();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickRegister");
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
