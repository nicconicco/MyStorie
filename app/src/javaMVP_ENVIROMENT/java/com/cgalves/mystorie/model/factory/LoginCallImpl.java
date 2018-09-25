package com.cgalves.mystorie.model.factory;

import android.content.Context;
import android.util.Log;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.parse.ParseUser;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carlos Nicolau Galves on 11/07/18.
 */

public class LoginCallImpl extends LoginAbstractCall {

    private static final String TAG = LoginCallImpl.class.getSimpleName();

    public LoginCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void login(String username, String password) {
        Log.d(TAG, "LoginCallImpl.login()");
        ParseUser.logInInBackground(username,password, (parseUser, e) -> {
            if (parseUser != null) {
                bus.post(parseUser);
            } else {
                ParseUser.logOut();
                bus.post(e.getMessage());
            }
        });
    }
}
