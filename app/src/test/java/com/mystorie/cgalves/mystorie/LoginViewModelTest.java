package com.mystorie.cgalves.mystorie;

import android.arch.lifecycle.MutableLiveData;

import com.cgalves.mystorie.MyStorieApplication;
import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.model.Error;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.model.UserResponse;

import org.junit.Test;

import mvvm.feature.login.vm.LoginViewModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginViewModelTest {

    @Test
    public void login_shouldCallLogin() {
        LoginViewModel vm = new LoginViewModel(mock(MyStorieApplication.class));
        vm.loginAbstractCall = mock(LoginAbstractCall.class);
        String login = "nicco";
        String senha = "senha";
        vm.login(login,senha);
        verify(vm.loginAbstractCall).login(login, senha);
    }

    @Test
    public void loginResult_shouldSetUserResponse() {
        LoginViewModel vm = spy(new LoginViewModel(mock(MyStorieApplication.class)));
        MutableLiveData<UserResponse> mock = mock(MutableLiveData.class);
        vm.userResponse = mock;

        User user = new User();
        user.setToken("123");

        UserResponse userResponse = new UserResponse(user);

        when(vm.createUserResponseSuccessful(user)).thenReturn(userResponse);

        vm.onLoginResultCall(user);

        verify(vm.userResponse).setValue(userResponse);
        verify(vm.getApplication()).setToken(user.getToken());
    }

    @Test
    public void loginResultError_shouldSetUserResponseError() {
        LoginViewModel vm = spy(new LoginViewModel(mock(MyStorieApplication.class)));
        MutableLiveData<UserResponse> mock = mock(MutableLiveData.class);
        vm.userResponse = mock;

        String error = "error";

        UserResponse userResponse = new UserResponse(new Error(error));

        when(vm.createUserResponseError(error)).thenReturn(userResponse);

        vm.onError(error);

        verify(vm.userResponse).setValue(userResponse);
    }
}
