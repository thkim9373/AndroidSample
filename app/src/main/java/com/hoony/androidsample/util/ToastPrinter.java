package com.hoony.androidsample.util;

import android.content.Context;
import android.widget.Toast;

public class ToastPrinter {
    private static Toast TOAST;

    public static void showToast(Context context, String msg) {
        if (TOAST != null) TOAST.cancel();
        TOAST = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        TOAST.show();
    }
}
