package com.cgalves.mystorie.common.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.ListSectionAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.NoticiasAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.NovidadesAbstractCall;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public abstract class APIAbstractFactory {

    public static APIAbstractFactory getFactory(Context context){
        return new FactoryImpl();
    }

    public abstract LoginAbstractCall getLoginCall(EventBus bus, Context context);
    public abstract HomeAbstractCall getHomeCall(EventBus bus, Context context);
    public abstract ListSectionAbstractCall getListSectionCall(EventBus bus, Context context);
    public abstract NoticiasAbstractCall getNoticiasCall(EventBus bus, Context context);
    public abstract NovidadesAbstractCall getNovidadesCall(EventBus bus, Context context);
}
