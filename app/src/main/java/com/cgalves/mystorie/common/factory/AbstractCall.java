package com.cgalves.mystorie.common.factory;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public class AbstractCall {

    protected EventBus bus;
    protected Context context;

    public AbstractCall(EventBus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    protected void post(Object t,EventBus bus) {
        if (!bus.hasSubscriberForEvent(t.getClass()))
            bus.postSticky(t);
        else
            bus.post(t);
    }
}
