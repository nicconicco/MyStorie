package com.cgalves.mystorie.feature.splash;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.cgalves.mystorie.feature.login.view.LoginActivity_;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.utils.AlertDialogUtils;
import com.cgalves.mystorie.common.utils.AndroidUtils;
import com.parse.Parse;
import com.parse.ParseInstallation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @AfterViews
    void init() {
        initParseServer();

        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.state_after_view));
        if (AndroidUtils.isNotKeepActivitiesEnable(this)) {
            AlertDialogUtils.showAlertWarning(this, this.getString(R.string.not_keep_activities_mode_info));
            AndroidUtils.getActualDeviceInformation();
        } else {
            new Handler().postDelayed(() -> {
                LoginActivity_.intent(getBaseContext()).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }, 3000);
        }
    }

    private void initParseServer() {
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
