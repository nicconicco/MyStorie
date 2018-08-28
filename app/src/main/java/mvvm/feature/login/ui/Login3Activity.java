package mvvm.feature.login.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.feature.admin.home.MasterHomeActivity_;
import com.cgalves.mystorie.feature.home.view.activity.HomeActivity_;

import org.androidannotations.annotations.EActivity;

import mvvm.feature.login.vm.LoginViewModelSemEventBus;

import static com.facebook.FacebookSdk.getApplicationContext;

@EActivity(R.layout.activity_login_activity3)
public class Login3Activity extends AppCompatActivity {

    private Observer<User> observer;

    public void onLoginClick(View view) {
        observer = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user.getIsAdmin()) {
                    MasterHomeActivity_.intent(Login3Activity.this).start();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    HomeActivity_.intent(Login3Activity.this).start();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        };

        LoginViewModelSemEventBus loginViewModelSemEventBus = ViewModelProviders.of(this, new ViewModelFactory()).get(LoginViewModelSemEventBus.class);
        loginViewModelSemEventBus.getUser().observe(this, observer);
    }
}

class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new LoginViewModelSemEventBus(getApplicationContext());
    }
}


