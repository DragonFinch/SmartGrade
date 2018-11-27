package com.school.nfcard.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 此类的作用：Toast  工具类
 * <p>
 * Created by Liu on 2018/6/11.
 */
public class ToastUtil {

    private Toast toast;

    private static ToastUtil mInstance;

    public static ToastUtil getInstance() {
        if (mInstance == null) {
            synchronized (ToastUtil.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtil();
                }
            }
        }
        return mInstance;
    }


    public void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
