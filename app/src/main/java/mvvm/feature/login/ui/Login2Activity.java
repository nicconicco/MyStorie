package mvvm.feature.login.ui;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.databinding.ActivityLogin2Binding;
import com.cgalves.mystorie.feature.admin.home.MasterHomeActivity_;
import com.cgalves.mystorie.feature.home.view.activity.HomeActivity_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import mvvm.feature.login.vm.LoginViewModel;

@EActivity(R.layout.activity_login2)
public class Login2Activity extends BaseActivity {

    ActivityLogin2Binding binding;

    @Bean
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        setView();
        setStateOfObservablesOnView();
    }

    private void setStateOfObservablesOnView() {

        binding.getViewModel().progressLoadingState.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                boolean isLoading = binding.getViewModel().progressLoadingState.get();
                if (isLoading) {
                    progressBar.show(getSupportFragmentManager(), "Whatever");
                } else {
                    progressBar.dismiss();
                }
            }
        });


        binding.getViewModel().changeView.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                boolean changeView = binding.getViewModel().changeView.get();

                if(changeView) {
                    binding.getViewModel().isAdmin.notifyChange();
                }
            }
        });

        binding.getViewModel().isAdmin.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                boolean isAdmin = binding.getViewModel().isAdmin.get();
                if (isAdmin) {
                    MasterHomeActivity_.intent(Login2Activity.this).start();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    HomeActivity_.intent(Login2Activity.this).start();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });
    }

    public void onLoginClick(View view) {
        binding.getViewModel().onClickDoLogin();
    }

    private void setView() {
        setData();
    }

    private void setData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login2);
        binding.setViewModel(loginViewModel);
        binding.executePendingBindings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginViewModel.register();
        setData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loginViewModel.unregister();
    }
}
