package com.rdw.library.alertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.rdw.library.R;
import com.rdw.library.interfacemethods.RwAlertDialogListener;
import com.rdw.library.interfacemethods.RwAlertDialogOkListener;

/*** Created by RAHUL D. WADHAI on 7/14/2017 ***/

public class RwAlertDialog {
    /*  Use this dialog for two buttons*/

    public static void alertDialog(Context context, String title, String message, String positiveButton,
                                   String negativeButton, final int resultCode, boolean setCancel,
                                   final RwAlertDialogListener alertDialogListener){
        AlertDialog.Builder alert = new AlertDialog.Builder(context, R.style.customDialogue);
        alert.setCancelable(setCancel);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(positiveButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {}});
        alert.setNegativeButton(negativeButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {}});

        final AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogListener.onDialogOk(resultCode);
                alertDialog.dismiss();
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogListener.onDialogCancel(resultCode);
                alertDialog.dismiss();
            }
        });
    }

    /*
    Use this dialog for single button
   */
    public static void  alertDialogOk(Context context, String title, String message,
                                      String button, final int resultCode, boolean setCancel,
                                      final RwAlertDialogOkListener alertDialogOkListener){
        final AlertDialog.Builder alert = new AlertDialog.Builder(context,R.style.customDialogue);
        alert.setCancelable(setCancel);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {}});

        final AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.dialogTheme;
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogOkListener.onDialogOk(resultCode);
                alertDialog.dismiss();
            }
        });
    }
}
