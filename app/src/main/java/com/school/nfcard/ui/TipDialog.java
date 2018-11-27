package com.school.nfcard.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatTextView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.school.nfcard.R;

import java.io.File;

/**
 * 此类的作用：自定义下载进度框
 * <p>
 * Created by LiuHW on 2017/8/15.
 */

public class TipDialog extends Dialog {

    private Context context;
    private ImageView imageView;
    private AppCompatTextView textName;


    public TipDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public TipDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_warning, null);
        setContentView(view);
        imageView = view.findViewById(R.id.imageHead);
        textName = view.findViewById(R.id.textName);
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        Display d = manager.getDefaultDisplay();
        params.width = 800;
        params.height = 500;
        dialogWindow.setAttributes(params);
        setCancelable(false);
    }

    public void showDialog(String name, String head) {
        show();
        if (name != null && head != null) {
            if (this.isShowing()) {
                textName.setText(name);
                Glide.with(context).load(head).into(imageView);
            }
        }
    }


    public void dismisDialog() {
        dismiss();
    }
}
