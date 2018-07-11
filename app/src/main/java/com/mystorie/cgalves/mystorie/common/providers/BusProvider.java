package com.mystorie.cgalves.mystorie.common.providers;


import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Scopus on 11/07/18.
 */

@EBean(scope = EBean.Scope.Singleton)
public class BusProvider {

    private EventBus bus;

    @AfterInject
    void init() {
        bus = EventBus.getDefault();
    }

    public EventBus bus() {
        return bus;
    }
}


