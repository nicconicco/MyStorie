package com.cgalves.mystorie.common.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cgalves.mystorie.R;

public final class CustomProgressDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(null);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.progress_bar, null);
    }

    //InsureDialog(this, lastChecked, listInsures).show(supportFragmentManager, TAG)
//    private Dialog dialog;
//
//    public Dialog show(Context context) {
//        return show(context, null);
//    }
//
//    public Dialog show(Context context, CharSequence title) {
//        return show(context, title, false);
//    }
//
//    public Dialog show(Context context, CharSequence title, boolean cancelable) {
//        return show(context, title, cancelable, null);
//    }
//
//
//    public Dialog show(Context context, CharSequence title, boolean cancelable,
//                       SearchManager.OnCancelListener cancelListener) {
//        LayoutInflater inflator = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View view = inflator.inflate(R.layout.progress_bar, null);
//        if(title != null) {
//            final TextView tv = (TextView) view.findViewById(R.id.id_title);
//            tv.setText(title);
//        }
//
//        dialog = new Dialog(context, R.style.NewDialog);
//        dialog.setContentView(view);
//        dialog.setCancelable(cancelable);
//        dialog.setOnCancelListener((DialogInterface.OnCancelListener) cancelListener);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        dialog.show();
//
//        return dialog;
//    }
//
//    public Dialog getDialog() {
//        return dialog;
//    }
}
