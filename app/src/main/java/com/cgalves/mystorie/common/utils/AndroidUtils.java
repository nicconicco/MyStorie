package com.cgalves.mystorie.common.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.cgalves.mystorie.BuildConfig;

/**
 * Created by Scopus on 10/07/18.
 */

public final class AndroidUtils {

    private AndroidUtils(){}

    public static boolean isNotKeepActivitiesEnable(Context context) {
        boolean enabled;

        if (BuildConfig.DEBUG) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= 23) {
            enabled = Settings.Global.getInt(context.getContentResolver(), Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0) == 1;
        } else {
            enabled = Settings.System.getInt(context.getContentResolver(), Settings.System.ALWAYS_FINISH_ACTIVITIES, 0) == 1;
        }

        return enabled;
    }

    public static DeviceInformation getActualDeviceInformation() {
        return new DeviceInformation(Build.MANUFACTURER, Build.MODEL);
    }
}
