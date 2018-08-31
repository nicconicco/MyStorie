package mvvm.common;

import android.app.Application;
import android.arch.lifecycle.*;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.cgalves.mystorie.MyStorieApplication;
import com.cgalves.mystorie.common.providers.BusProvider;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by scopus on 28/08/18.
 */

public class BaseViewModel extends AndroidViewModel {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public EventBus bus = EventBus.getDefault();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        EventBus.getDefault().register(this);
    }

    @NonNull
    @Override
    public MyStorieApplication getApplication() {
        return super.getApplication();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
