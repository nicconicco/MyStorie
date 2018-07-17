package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;

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
        // Estou no mock, por isso posso passar o que quiser.
        bus.post("token:k5kw4IpSv2xJ59YqGAUw03zOtuSGdOzK");
    }
}
