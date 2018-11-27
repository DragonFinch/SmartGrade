package com.school.nfcard.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.demo.moddle.presenter.HandlerError;
import com.school.nfcard.api.ApiFactory;
import com.school.nfcard.entity.Binding;
import com.school.nfcard.entity.ClassInfo;
import com.school.nfcard.entity.LoginRes;
import com.school.nfcard.entity.SchoolInfo;
import com.school.nfcard.entity.help.ClassInfoHelper;
import com.school.nfcard.entity.help.SchoolHelper;
import com.school.nfcard.presenter.base.BasePresenter;
import com.school.nfcard.presenter.http.CallBack;
import com.school.nfcard.presenter.http.TransformUtils;
import com.school.nfcard.presenter.impl.LoginContract;
import com.school.nfcard.utils.NetUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 此类的作用：登录功能
 * <p>
 * Created by Liu on 2018/6/11.
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract {


    public LoginPresenter(LoginContract.View view) {
        attachView(view);
    }


    @Override
    public void login(@NotNull Context context, @NotNull String phone, @NotNull String password) {
        if (TextUtils.isEmpty(phone)) {
            getView().showErrorMessage("请输入代理商编号");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            getView().showErrorMessage("请输入代理商密码");
            return;
        }
        if (isViewBind()) {
            ApiFactory.createLogin10Service()
                    .login(phone, password)
                    .compose(TransformUtils.<LoginRes>mainThread())
                    .subscribe(new CallBack<LoginRes>() {
                        @Override
                        public void onError(Throwable e) {
                            HandlerError.INSTANCE.handlerError(getView(), e);
                        }

                        @Override
                        public void successful(LoginRes body) {
                            String sucess = body.getSuccess();
                            String erro = body.getErrormsg();
                            if (!TextUtils.isEmpty(sucess)) {
                                List<SchoolInfo> list = body.getData();
                                if (list != null && list.size() > 0) {
                                    SchoolHelper.deleteAll();
                                    ClassInfoHelper.deleteAll();
                                    for (SchoolInfo info : list) {
                                        SchoolHelper.insert(info);
                                        List<ClassInfo> classInfos = info.getClassInfos();
                                        if (classInfos != null && classInfos.size() > 0) {
                                            for (ClassInfo classInfo : classInfos) {
                                                ClassInfoHelper.insert(classInfo);
                                            }
                                        }
                                    }
                                    getView().loginSuccess();
                                } else {
                                    getView().showErrorMessage("没有班级信息");
                                }
                            } else {
                                if (!TextUtils.isEmpty(erro)) {
                                    getView().showErrorMessage(erro);
                                }
                            }
                        }

                        @Override
                        public void beginStart() {

                        }
                    });
        }

    }

    @Override
    public void bindingDevice(@NotNull Context context, @NotNull String schoolid, @NotNull String classid, @NotNull String equipmentid) {
        if (isViewBind()) {
            ApiFactory.createLogin10Service()
                    .bindingDevice(schoolid, classid, equipmentid)
                    .compose(TransformUtils.<Binding>mainThread())
                    .subscribe(new CallBack<Binding>() {
                        @Override
                        public void onError(Throwable e) {
                            HandlerError.INSTANCE.handlerError(getView(), e);
                        }

                        @Override
                        public void successful(Binding body) {
                            String sucess = body.getSuccess();
                            String erro = body.getErrormsg();
                            if (!TextUtils.isEmpty(sucess)) {
                                getView().bindingSuccess();
                            } else {
                                if (!TextUtils.isEmpty(erro)) {
                                    getView().showErrorMessage(erro);
                                }
                            }
                        }

                        @Override
                        public void beginStart() {

                        }
                    });
        }

    }
}
