package com.cgalves.mystorie.common.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;


/**
 *
 * @author R/GA
 *
 */
public class ProgressoDialog extends BaseDialog {


    private boolean cancelable = false;
    private boolean cancelableOnTouchOutSide;
    private boolean isShown;

    public ProgressoDialog() {
        notCancellable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_progresso, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(cancelable);
        getDialog().setCanceledOnTouchOutside(cancelableOnTouchOutSide);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, android.view.KeyEvent event) {
                return (keyCode ==  android.view.KeyEvent.KEYCODE_BACK) != cancelable;
            }
        });
        return view;
    }

    public boolean isShown(){
        return isShown;
    }

    @Override
    public BaseDialog show(BaseActivity activity) {
        isShown = true;
        return super.show(activity);
    }

    @Override
    public BaseDialog show(Activity activity) {
        isShown = true;
        return super.show(activity);
    }

    public BaseDialog show(Activity activity, boolean cancelable, boolean cancelableOnTouchOutSide) {
        setCancelable(cancelable);
        setCancelableOnTouchOutSide(cancelableOnTouchOutSide);
        return this.show(activity);
    }

    public BaseDialog showAllowingStateLoss(Activity activity) {
        isShown = true;
        return super.showAllowingStateLoss(activity);
    }

    @Override
    public void dismiss() {
        isShown = false;
        super.dismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(STYLE_NO_FRAME, R.style.ProgressDialog);
    }

    public void notCancellable() {
        setCancelable(false);
    }

    @Override
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setCancelableOnTouchOutSide(boolean cancelableOnTouchOutSide) {
        this.cancelableOnTouchOutSide = cancelableOnTouchOutSide;
    }
}