package com.cgalves.mystorie.common.abstractcalls;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

public abstract class ContactAbstractCall extends AbstractCall {

    public ContactAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void findInformationUser();
}
