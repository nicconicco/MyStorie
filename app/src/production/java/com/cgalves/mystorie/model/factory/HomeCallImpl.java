package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;

import org.greenrobot.eventbus.EventBus;

public class HomeCallImpl extends HomeAbstractCall {
    public HomeCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findImageTopHeader(String token) {

    }

    @Override
    public void findSectionsInBody(String token) {

    }
}
