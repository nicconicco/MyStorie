package com.mystorie.cgalves.mystorie.common.factory;

import android.content.Context;

import com.mystorie.cgalves.mystorie.model.factory.LoginCallImpl;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

class FactoryImpl extends APIAbstractFactory {

    @Override
    public LoginAbstractCall getLoginCall(EventBus bus, Context context) {
        return new LoginCallImpl(bus, context);
    }
}
