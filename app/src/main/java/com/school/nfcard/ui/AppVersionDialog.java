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
import android.widget.TextView;

import com.school.nfcard.R;


/**
 * 此类的作用：App升级的对话框
 * <p>
 * Created by LiuHW on 2017/7/29.
 */

public class AppVersionDialog extends Dialog {

    private Context context;
    private String title;
    private TextView dialogcontent;
    private TextView cancle;
    private TextView sure;
    private View line;

    public AppVersionDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public AppVersionDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void hideCancle() {
        if (this.isShowing()) {
            cancle.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
    }


    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_app_version, null);
        setContentView(view);
        dialogcontent = (TextView) view.findViewById(R.id.text_appver_dialog_content);
        cancle = (TextView) view.findViewById(R.id.text_appver_dialog_cancle);
        sure = (TextView) view.findViewById(R.id.text_appver_dialog_sure);
        line = view.findViewById(R.id.line_appver_dialog);
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        Display d = manager.getDefaultDisplay();
        params.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(params);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appClickLister.cancleDown();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appClickLister.sureDown();
            }
        });

    }

    public void setDialogContent(String content) {
        dialogcontent.setText(content);
    }


    public interface DialogItemClickListener {

        void sureDown();

        void cancleDown();
    }


    private DialogItemClickListener appClickLister;

    public DialogItemClickListener getAppClickLister() {
        return appClickLister;
    }

    public void setAppClickLister(DialogItemClickListener appClickLister) {
        this.appClickLister = appClickLister;
    }
}
