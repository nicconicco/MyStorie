package com.cgalves.mystorie.common.abstractcalls;

import android.content.Context;

import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.Section;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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
