package com.cgalves.mystorie.model.factory;

import android.content.Context;
import android.util.Log;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.model.UserRegistrationVO;
import com.parse.ParseUser;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public class LoginCallImpl extends LoginAbstractCall {


    private static String TAG = LoginCallImpl.class.getName();

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
        user2.user.setName("Fake Name");
        user2.user.setToken("Fake Token");
        user2.user.setEmail("Fake Email");
        user2.user.setIsAdmin(false);
        bus.post(user2);

        // TODO; INTEGRACAO ENVIROMENT
//        ParseUser user = new ParseUser();
//        user.setUsername(username);
//        user.setEmail(password);
//        user.setPassword(email);
//        user.signUpInBackground(e -> {
//            try {
//                if (e == null) {
//                    ParseUser result = ParseUser.getCurrentUser();
//                    User user2 = new User();
//
//                    user2.setName(result.getUsername());
//                    user2.setToken(result.getSessionToken());
//                    user2.setEmail(result.getEmail());
//
//                    try {
//                        user2.setIsAdmin((Boolean) result.get("admin"));
//                    } catch (Exception e2) {
//                        bus.post(e2.getMessage());
//                        Log.e(TAG, e2.getMessage());
//                    }
//
//                    bus.post(user2);
//                } else {
//                    ParseUser.logOut();
//                    bus.post(e.getMessage());
//                }
//            } catch (Exception error) {
//                Log.e(TAG, error.getMessage());
//                bus.post(error.getMessage());
//            }
//        });
    }
}
