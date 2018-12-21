package com.cgalves.mystorie.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgalves.mystorie.R;

/**
 * Created by Scopus on 10/07/18.
 */

public final class AlertDialogUtils {
    public static void showAlertWarning(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.warning_dialog);
        dialog.setTitle(activity.getString(R.string.app_name));

        TextView text = dialog.findViewById(R.id.text);
        text.setText(msg);

        LinearLayout dialogButton = dialog.findViewById(R.id.btn_exit);
        dialogButton.setOnClickListener(v -> activity.finish());

        dialog.show();
    }
}
