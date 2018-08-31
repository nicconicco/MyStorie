package mvvm.feature.home.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.cgalves.mystorie.MyStorieApplication;
import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.Error;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.model.UserResponse;
import com.cgalves.mystorie.feature.home.model.ImageResponse;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.home.model.SectionResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class HomeViewModel extends AndroidViewModel{
    public MutableLiveData<ImageResponse> imageResponseMutableLiveData;
    public MutableLiveData<SectionResponse> sectionResponseMutableLiveData;
    @VisibleForTesting
    public HomeAbstractCall homeAbstractCall;;
    @VisibleForTesting
    public EventBus bus = EventBus.getDefault();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeAbstractCall = getHomeCall();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }

    public LiveData<ImageResponse> fetchList(String token) {
        if (imageResponseMutableLiveData == null) {
            imageResponseMutableLiveData = new MutableLiveData<ImageResponse>();
        }
        homeAbstractCall.findImageTopHeader(token);
        return imageResponseMutableLiveData;
    }

    public LiveData<SectionResponse> fetchSection(String token) {
        if (sectionResponseMutableLiveData == null) {
            sectionResponseMutableLiveData = new MutableLiveData<SectionResponse>();
        }
        homeAbstractCall.findSectionsInBody(token);
        return sectionResponseMutableLiveData;
    }

    private HomeAbstractCall getHomeCall() {
        return APIAbstractFactory.getFactory(getApplication()).getHomeCall(bus, getApplication());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageTopHeaderLoaded(ImageResponse imageResponse) {
        imageResponseMutableLiveData.setValue(new ImageResponse(imageResponse.getList()));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSectionBodyLoaded(List<Section> list) {
        sectionResponseMutableLiveData.setValue(new SectionResponse(list));
    }

    @NonNull
    @Override
    public MyStorieApplication getApplication() {
        return super.getApplication();
    }
}
