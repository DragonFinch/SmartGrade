package com.school.nfcard.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.school.nfcard.R;

/**
 * 此类的作用：自定义下载进度框
 * <p>
 * Created by LiuHW on 2017/8/15.
 */

public class LodingDialog extends Dialog {

    private Context context;
    private ImageView loding;

    public LodingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public LodingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loding, null);
        setContentView(view);
        loding = (ImageView) view.findViewById(R.id.image_dialog_loading);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        loding.startAnimation(hyperspaceJumpAnimation);
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        Display d = manager.getDefaultDisplay();
        params.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(params);
        setCancelable(false);
    }
}
