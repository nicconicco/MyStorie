package com.mystorie.cgalves.mystorie.model.factory;

import android.content.Context;

import com.mystorie.cgalves.mystorie.common.factory.LoginAbstractCall;

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
        // Estou no ENVIROMENT, por isso posso passar o que quiser.
        bus.post("LOGIN COMPLETO ENVIROMENT");
    }
}
