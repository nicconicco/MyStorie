package com.cgalves.mystorie.model.factory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.model.UserRegistrationVO;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public class LoginCallImpl extends LoginAbstractCall {

    public LoginCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    public LoginCallImpl(MutableLiveData<User> userLiveData, Context context) {
        super(userLiveData, context);
    }

    @Override
    public void loginWithMVVM(MutableLiveData<User> user) {
        User p = new User();
        p.setName("Fake Test");
        p.setIsAdmin(false);
        user.postValue(p);
    }

    @Override
    public void login(String username, String password) {
        User p = new User();
        p.setName("Fake Test");
        p.setIsAdmin(false);
        bus.post(p);
    }

    @Override
    public void doRegistration(String username, String password, String email) {
        UserRegistrationVO user2 = new UserRegistrationVO();
        user2.user.setName("Fake Name");
        user2.user.setToken("Fake Token");
        user2.user.setEmail("Fake Email");
        user2.user.setIsAdmin(false);
        bus.post(user2);
    }
}
