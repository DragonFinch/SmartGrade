package com.school.nfcard.presenter.impl;

import android.content.Context;

import com.school.nfcard.presenter.base.BaseView;

/**
 * 此类的作用：下载文件
 * <p>
 * Created by LiuHW on 2017/8/4.
 */

public interface DownFileContract {


    void downLoadAppByUrl(Context context, String url);

    interface View extends BaseView {

        void downLoadAppSucsess();
    }
}
