package com.mystorie.cgalves.mystorie;

import android.app.Application;
import android.content.Context;

import com.cgalves.mystorie.feature.login.presenter.LoginContract;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl_;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

/**
 * Created by scopus on 03/08/18.
 */

public class LoginPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Context mMockContext;

    @Mock
    LoginContract.LoginPresenterView view;

    public LoginPresenterImpl_ loginPresenterImpl_;

    @Before
    public void beforeTest() {
        loginPresenterImpl_ = LoginPresenterImpl_.getInstance_(mMockContext);
        loginPresenterImpl_.attachView(view);
        loginPresenterImpl_.register();
    }

    @Test
    public void doLoginFalse() throws Exception {
        loginPresenterImpl_.doLogin("", "");
        verify(view).onLoginResult(false);
    }

    @After
    public  void afterTest() {
        loginPresenterImpl_.unregister();
        loginPresenterImpl_.detachView();
    }
}
