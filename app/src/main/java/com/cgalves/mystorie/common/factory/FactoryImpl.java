package com.cgalves.mystorie.common.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.ListSectionAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.model.factory.HomeCallImpl;
import com.cgalves.mystorie.model.factory.ListSectionCallImpl;
import com.cgalves.mystorie.model.factory.LoginCallImpl;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

class FactoryImpl extends APIAbstractFactory {

    @Override
    public LoginAbstractCall getLoginCall(EventBus bus, Context context) {
        return new LoginCallImpl(bus, context);
    }

    @Override
    public HomeAbstractCall getHomeCall(EventBus bus, Context context) {
        return new HomeCallImpl(bus, context);
    }

    @Override
    public ListSectionAbstractCall getListSectionCall(EventBus bus, Context context) {
        return new ListSectionCallImpl(bus, context);
    }
}
