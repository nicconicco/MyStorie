package mvvm.feature.login.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.Error;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.model.UserResponse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mvvm.common.BaseViewModel;

public class LoginViewModel extends BaseViewModel {
    @VisibleForTesting
    public MutableLiveData<UserResponse> userResponse;

    @VisibleForTesting
    public LoginAbstractCall loginAbstractCall;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginAbstractCall = getLoginCall();
    }

    public LiveData<UserResponse> login(String login, String senha) {
        if (userResponse == null) {
            userResponse = new MutableLiveData<>();
        }
        loginAbstractCall.login(login, senha);
        return userResponse;
    }

    private LoginAbstractCall getLoginCall() {
        return APIAbstractFactory.getFactory(getApplication()).getLoginCall(bus, getApplication());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLoginResultCall(User resultLogin) {
        getApplication().setToken(resultLogin.getToken());
        userResponse.setValue(new UserResponse(resultLogin));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onError(String error) {
        userResponse.setValue(new UserResponse(new Error(error)));
    }
}
