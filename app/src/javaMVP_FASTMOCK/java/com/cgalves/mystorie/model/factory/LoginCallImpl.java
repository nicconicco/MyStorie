package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.parse.ParseUser;

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
        ParseUser p = new ParseUser();
        bus.post(p);
    }
}
