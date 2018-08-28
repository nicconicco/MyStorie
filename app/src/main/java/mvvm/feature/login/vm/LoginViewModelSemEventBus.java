package mvvm.feature.login.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.User;


import mvvm.common.BaseViewModelSemEventBus;

/**
 * Created by scopus on 28/08/18.
 */

public class LoginViewModelSemEventBus extends BaseViewModelSemEventBus {

    private final MutableLiveData<User> user;
    LoginAbstractCall loginAbstractCall;

    public LoginViewModelSemEventBus(Context context) {
        this.user = new MutableLiveData<>();
        loginAbstractCall = APIAbstractFactory.getFactory(context).getLoginCall(user, context);
        loginAbstractCall.loginWithMVVM(user);
    }

    public LiveData<User> getUser() {
        return user;
    }

}
