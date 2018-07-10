package com.mystorie.cgalves.mystorie.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystorie.cgalves.mystorie.R;

/**
 * Created by Scopus on 10/07/18.
 */

public class AlertDialogUtils {

    public static void showAlertWarning(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.warning_dialog);
        dialog.setTitle(activity.getString(R.string.app_name));

        TextView text = dialog.findViewById(R.id.text);
        text.setText(msg);
        ImageView image = dialog.findViewById(R.id.image);

        LinearLayout dialogButton = dialog.findViewById(R.id.btn_exit);
        dialogButton.setOnClickListener(v -> activity.finish());

        dialog.show();
    }
}
