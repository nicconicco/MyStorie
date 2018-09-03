package mvvm.common;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.cgalves.mystorie.MyStorieApplication;

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
