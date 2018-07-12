package com.cgalves.mystorie.common.factory;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public abstract class APIAbstractFactory {

    public static APIAbstractFactory getFactory(Context context){
        return new FactoryImpl();
    }

    public abstract LoginAbstractCall getLoginCall(EventBus bus, Context context);
}
