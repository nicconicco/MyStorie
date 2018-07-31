package com.cgalves.mystorie.common.abstractcalls;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by scopus on 27/07/18.
 */

public abstract class NovidadesAbstractCall extends AbstractCall {
    public NovidadesAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void findNovidades(String noticias);
}
