package com.cgalves.mystorie.common.utils;

import android.app.Activity;

public class ViewUtils {
    public static void showAlert(Activity act, final String texto) {
        AlertDialogUtils.showAlertWarning(act, texto);
    }
}
