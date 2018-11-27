package com.school.nfcard.presenter.impl;

import android.content.Context;

import com.school.nfcard.presenter.base.BaseView;

import java.util.List;

/**
 * 此类的作用：文件上传测试
 * <p>
 * Created by LiuHW on 2017/8/7.
 * <p>
 * 邮箱：zixuan06010@126.com
 */

public interface UploadFileContrat {

    void uploadFile(String sign, String path);


    void uploadFileOSS(Context context, String path);


    interface View extends BaseView {
        void uploadSucess(String url);
    }
}
