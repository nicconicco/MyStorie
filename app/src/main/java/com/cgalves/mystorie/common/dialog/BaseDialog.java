package com.cgalves.mystorie.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.cgalves.mystorie.common.activity.BaseActivity;

/**
 *
 * @author R/GA
 *
 */

public abstract class BaseDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public BaseDialog show(BaseActivity activity) {
        if (activity!= null && !activity.isFinishing() && !activity.isDestroyed()) {
            this.show(activity.getFragmentManager(), getClass().getSimpleName());
        }
        return this;
    }

    public BaseDialog show(Activity activity) {
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            this.show(activity.getFragmentManager(), getClass().getSimpleName());
        }
        return this;
    }

    public BaseDialog showAllowingStateLoss(Activity activity) {
        if (activity!= null && !activity.isFinishing() && !activity.isDestroyed()) {
            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            ft.add(this, getClass().getSimpleName());
            ft.commitAllowingStateLoss();
        }
        return this;
    }

    public void backPressed() {
        if (getDialog() != null)
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                        dismiss();
                        return true;
                    } else
                        return false;
                }
            });
    }

    @Override
    public void onResume() {
        backPressed();
        super.onResume();
    }
}
