package com.cgalves.mystorie.common.abstractcalls;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

public abstract class HomeAbstractCall extends AbstractCall {

    public HomeAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void findImageTopHeader(String token);
    public abstract void findSectionsInBody(String token);
}
