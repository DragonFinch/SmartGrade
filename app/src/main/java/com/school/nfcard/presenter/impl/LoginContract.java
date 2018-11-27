package com.school.nfcard.presenter.impl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.school.nfcard.entity.LoginRes;
import com.school.nfcard.entity.SchoolInfo;
import com.school.nfcard.presenter.base.BaseView;

import java.util.List;

/**
 * 此类的作用：XXXXXX
 * <p>
 * Created by Liu on 2018/9/14.
 */
public interface LoginContract {

    void login(Context context, String phone, String password);

    void bindingDevice(Context context, String schoolid, String classid, String equipmentid);

    interface View extends BaseView

    {
        void loginSuccess();


        void bindingSuccess();
    }


}
