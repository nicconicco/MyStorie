package mvvm.feature.splash.vm;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.utils.AlertDialogUtils;
import com.cgalves.mystorie.common.utils.AndroidUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
import com.parse.ParseInstallation;

import mvvm.feature.login.ui.Login2Activity;
import mvvm.feature.login.ui.Login2Activity_;

/**
 * Created by scopus on 28/08/18.
 */

public class Splash2ViewModel  {

    public void onStartSplash(Activity activity) {
        Log.d(activity.getString(R.string.tag_next_flow), activity.getString(R.string.state_after_view));
        if (AndroidUtils.isNotKeepActivitiesEnable(activity)) {
            AlertDialogUtils.showAlertWarning(activity, activity.getString(R.string.not_keep_activities_mode_info));
            AndroidUtils.getActualDeviceInformation();
        } else {
            new Handler().postDelayed(() -> {
                startLoginActivity2(activity);
            }, 3000);
        }
    }

    private void startLoginActivity2(Activity activity) {
        Login2Activity_.intent(activity).start();
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }

    public void startParse(Activity activity) {
        Parse.initialize(activity);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public void startFacebook(Activity activity) {
        FacebookSdk.sdkInitialize(activity);
        AppEventsLogger.activateApp(activity);
    }
}
