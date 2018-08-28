package com.cgalves.mystorie.common.abstractcalls;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.cgalves.mystorie.common.model.User;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public abstract class LoginAbstractCall extends AbstractCall {


    private final MutableLiveData<User> userLiveData;

    public LoginAbstractCall(EventBus bus, Context context) {
        super(bus, context);
        userLiveData = null;
    }

    public LoginAbstractCall(MutableLiveData<User> userLiveData, Context context) {
        super(context);
        this.userLiveData = userLiveData;
    }

    public abstract void loginWithMVVM(MutableLiveData<User> user);

    public abstract void login(String username, String password);

    public abstract void doRegistration(String username, String password, String email);
}
