package com.rdw.library.progressdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rdw.library.R;

public class RwProgressDialog extends Dialog {

   // private AnimationDrawable animation;
    private int mDialogMessage;
    private int mColor;
    private int mProgressColor;
    private Context mContext;
    /**
     * @param context class context
     * @param dialogMessage use message in dialog
     * @param dialogMsgColor use color in dialog message
     */
    public RwProgressDialog(Context context, int dialogMessage, int dialogMsgColor, int progressColor) {
        super(context, R.style.TransparentProgressDialog);
        this.mDialogMessage = dialogMessage;
        this.mColor = dialogMsgColor;
        this.mProgressColor = progressColor;
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_custom_progress_layout);
        /*
             use this code for frame by frame animation
             ImageView la = (ImageView) findViewById(R.id.animation);
             la.setBackgroundResource(R.drawable.custom_progress_fram_animation);
             animation = (AnimationDrawable) la.getBackground();
         */
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView textViewMessage = findViewById(R.id.textViewMessage_Id);
        textViewMessage.setText(mDialogMessage);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,mProgressColor),
                PorterDuff.Mode.MULTIPLY);
    }

//    @Override
//    public void show() {
//        super.show();
//        animation.start();
//    }
//
//    @Override
//    public void dismiss() {
//        super.dismiss();
//        animation.stop();
//    }
}