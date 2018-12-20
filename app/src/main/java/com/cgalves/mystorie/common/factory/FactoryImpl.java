package com.cgalves.mystorie.common.factory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.ContactAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.ListSectionAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.NoticiasAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.NovidadesAbstractCall;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.model.factory.ContactCallImpl;
import com.cgalves.mystorie.model.factory.HomeCallImpl;
import com.cgalves.mystorie.model.factory.ListSectionCallImpl;
import com.cgalves.mystorie.model.factory.LoginCallImpl;
import com.cgalves.mystorie.model.factory.NoticiasCallImpl;
import com.cgalves.mystorie.model.factory.NovidadesCallImpl;

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

    @Override
    public NoticiasAbstractCall getNoticiasCall(EventBus bus, Context context) {
        return new NoticiasCallImpl(bus, context);
    }

    @Override
    public NovidadesAbstractCall getNovidadesCall(EventBus bus, Context context) {
        return new NovidadesCallImpl(bus, context);
    }

    @Override
    public ContactAbstractCall getContactCall(EventBus bus, Context context) {
        return new ContactCallImpl(bus, context);
    }
}
