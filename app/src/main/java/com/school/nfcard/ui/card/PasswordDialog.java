package com.school.nfcard.ui.card;

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
import android.widget.TextView;

import com.school.nfcard.R;


/**
 * 此类的作用：App升级的对话框
 * <p>
 * Created by LiuHW on 2017/7/29.
 */

public class PasswordDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView cancle;
    private TextView sure;

    public PasswordDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public PasswordDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_password, null);
        setContentView(view);
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        Display d = manager.getDefaultDisplay();
        params.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(params);
//        cancle.setOnClickListener(v -> dismiss());
//        sure.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onClick(View v) {

    }

}
