package com.mystorie.cgalves.mystorie;

import android.content.Context;

import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.model.UserRegistrationVO;
import com.cgalves.mystorie.feature.login.presenter.LoginContract;
import com.cgalves.mystorie.feature.login.presenter.LoginPresenterImpl_;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by scopus on 03/08/18.
 */
@RunWith(MockitoJUnitRunner.class)
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

//    @Test
//    public void doLoginFalse() throws Exception {
//        loginPresenterImpl_.doLogin("", "");
//        verify(view).onLoginResult(false);
//    }

    @Test
    public void doRegistrationCheck_User_Name_Return() {
        loginPresenterImpl_.doRegistration("","","");

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        UserRegistrationVO user2 = new UserRegistrationVO();
        user2.getUser().setName("Fake Name");
        verify(view).onResultRegistration(argument.capture());

        assertEquals(user2.getUser().getName(), argument.getValue().getName());
    }

    @After
    public  void afterTest() {
        loginPresenterImpl_.unregister();
        loginPresenterImpl_.detachView();
    }
}
