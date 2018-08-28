package mvvm.feature.login.vm;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.cgalves.mystorie.common.abstractcalls.LoginAbstractCall;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.model.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mvvm.common.BaseViewModel;

/**
 * Created by scopus on 28/08/18.
 */

@EBean
public class LoginViewModel extends BaseViewModel {

    LoginAbstractCall loginAbstractCall;

    public ObservableField<String> login = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableBoolean progressLoadingState = new ObservableBoolean();
    public ObservableBoolean changeView = new ObservableBoolean();
    public ObservableBoolean isAdmin = new ObservableBoolean();

    @AfterInject
    void init() {
        loginAbstractCall = APIAbstractFactory.getFactory(context).getLoginCall(busProvider.bus(), context);
    }

    public void onClickDoLogin() {
        String mLogin = login.get();
        String mPassword = password.get();

        if(mLogin == null || mPassword == null) {
            return;
        }

        if (mLogin.equals("") || mPassword.equals("")){
            return;
        }

        progressLoadingState.set(true);
        loginAbstractCall.login(mLogin, mPassword);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLoginResultCall(User resultLogin) {
        progressLoadingState.set(false);
        setToken(resultLogin);
        setUser(isAdmin(resultLogin));
    }

    private void setUser(boolean admin) {
        changeView.set(true);
        isAdmin.set(admin);
    }

    private void setToken(User result) {
        if (application != null) {
            application.setToken(result.getToken());
            application.setName(result.getName());
        }
    }

    private boolean isAdmin(User result) {
        if (result != null) {
            return result.getIsAdmin();
        }
        return false;
    }

    public void onClickLoginWithFacebook() {

    }

    public void onClickFirstAccess(){

    }
}
