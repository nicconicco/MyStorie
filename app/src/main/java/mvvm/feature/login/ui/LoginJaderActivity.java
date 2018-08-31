package mvvm.feature.login.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.feature.admin.home.MasterHomeActivity_;
import mvvm.feature.home.view.activity.HomeActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import mvvm.feature.login.vm.LoginViewModel;

@EActivity(R.layout.activity_login_v2)
public class LoginJaderActivity extends BaseActivity {

    @ViewById(R.id.et_username) EditText etUserName;
    @ViewById(R.id.et_password) EditText etPassword;

    @VisibleForTesting
    private LoginViewModel loginViewModel;

    @AfterViews
    void init() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Click(R.id.btn_login)
    void onClickLogin() {
        progressBar.show(getSupportFragmentManager(), "Whatever");
        loginViewModel.login(etUserName.getText().toString(), etPassword.getText().toString())
                .observe(this, userResponse -> {
            loginViewModel.userResponse.removeObservers(this);
            if(userResponse.isSuccess()){
                onLoginResult(userResponse.getUser().getIsAdmin());
                hideLoading();
            }else{
                onError(userResponse.getError().getMessage());
            }
        });
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : doLogin");
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickLogin");
    }

    public void onLoginResult(boolean isAdmin) {
        hideLoading();

        if (isAdmin) {
            goToMaster();
        } else {
            goToHome();
        }
    }

    private void hideLoading() {
        progressBar.dismiss();
    }

    @VisibleForTesting
    public void goToHome() {
        HomeActivity_.intent(this).start();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @VisibleForTesting
    public void goToMaster() {
        MasterHomeActivity_.intent(this).start();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onError(String error) {
        hideLoading();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
