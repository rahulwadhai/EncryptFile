package com.rdw.library.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/*** Created by RAHUL D. WADHAI on 7/14/2017 ***/

public class RwUtils {

    public static void messageLong(Context context, String toastMessage) {
        Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static void messageShort(Context context, String toastMessage) {
        Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
