package mvvm.feature.splash.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.databinding.ActivitySplash2Binding;

import mvvm.feature.splash.vm.Splash2ViewModel;

public class Splash2Activity extends BaseActivity {

    ActivitySplash2Binding binding;
    Splash2ViewModel splash2ViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setView();
        bindLoadSplash();
    }

    private void setView() {
        splash2ViewModel = new Splash2ViewModel();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash2);
        binding.setViewModel(splash2ViewModel);
        binding.executePendingBindings();
    }

    private void bindLoadSplash() {
        binding.getViewModel().onStartSplash(this);
        binding.getViewModel().startParse(this);
        binding.getViewModel().startFacebook(this);
    }
}
