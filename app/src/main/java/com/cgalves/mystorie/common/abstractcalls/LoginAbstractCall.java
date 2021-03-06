package com.cgalves.mystorie.common.abstractcalls;
import android.content.Context;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public abstract class LoginAbstractCall extends AbstractCall {

    public LoginAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void login(String username, String password);
}
