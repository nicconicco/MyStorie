package com.cgalves.mystorie.common.abstractcalls;

import android.content.Context;

import com.cgalves.mystorie.feature.home.model.Section;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by scopus on 25/07/18.
 */

public abstract class ListSectionAbstractCall extends AbstractCall {
    public ListSectionAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void findSection(Section section);
}
