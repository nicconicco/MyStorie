package com.cgalves.mystorie.common.presenter;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class BaseRepository {

    private static final String TAG = BaseRepository.class.getSimpleName();

    private Context context;

    public BaseRepository(Context context) {
        this();
        this.context = context;
    }

    private BaseRepository() {
        Log.d(TAG, "EventBus.register");

        EventBus.getDefault().removeAllStickyEvents();

        if (! EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void unregister() {
        Log.d(TAG, "EventBus.unregister");

        EventBus.getDefault().removeAllStickyEvents();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected Context getContext() {
        return this.context;
    }
}
