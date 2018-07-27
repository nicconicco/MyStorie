package com.cgalves.mystorie.common.abstractcalls;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by scopus on 27/07/18.
 */

public abstract class NoticiasAbstractCall extends AbstractCall {
    public NoticiasAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void findNoticias(String noticias);
}
