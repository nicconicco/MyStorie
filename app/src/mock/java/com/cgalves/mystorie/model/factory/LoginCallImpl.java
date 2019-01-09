package com.cgalves.mystorie.model.factory;

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
        user2.getUser().setName("Fake Name");
        user2.getUser().setToken("Fake Token");
        user2.getUser().setEmail("Fake Email");
        user2.getUser().setIsAdmin(false);
        bus.post(user2);
    }
}
