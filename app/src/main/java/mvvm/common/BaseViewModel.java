package mvvm.common;

import android.arch.lifecycle.*;
import android.content.Context;

import com.cgalves.mystorie.MyStorieApplication;
import com.cgalves.mystorie.common.providers.BusProvider;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by scopus on 28/08/18.
 */

@EBean
public class BaseViewModel extends android.arch.lifecycle.ViewModel implements ViewModel{

    @App
    protected MyStorieApplication application;

    @RootContext
    protected Context context;

    @Bean
    protected BusProvider busProvider;

    @Override
    public void register() {
        if (!busProvider.bus().isRegistered(this)) {
            busProvider.bus().register(this);
        }
    }

    @Override
    public void unregister() {
        if (busProvider.bus().isRegistered(this)) {
            busProvider.bus().unregister(this);
        }
    }
}
