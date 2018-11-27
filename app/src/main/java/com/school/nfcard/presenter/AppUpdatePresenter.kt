package com.school.nfcard.presenter

import android.text.TextUtils
import com.demo.moddle.presenter.HandlerError
import com.school.nfcard.api.ApiFactory
import com.school.nfcard.constant.AppConfig
import com.school.nfcard.constant.AppConfig.IP
import com.school.nfcard.entity.*
import com.school.nfcard.presenter.base.BasePresenter
import com.school.nfcard.presenter.http.CallBack
import com.school.nfcard.presenter.http.TransformUtils
import com.school.nfcard.presenter.impl.AppUpdateContract
import android.content.Context
import com.school.nfcard.utils.NetUtils


/**
 *此类的作用：获取联系人
 *
 * Created by Liu on 2018/8/13.
 *
 */
class AppUpdatePresenter(view: AppUpdateContract.View) : BasePresenter<AppUpdateContract.View>(), AppUpdateContract {

    init {
        attachView(view)
    }

    override fun checkAppVersion(context: Context) {
        if(!NetUtils.isInternetConnection(context)){
            return
        }
        //
        if (isViewBind) {
            ApiFactory.createLoginService()
                    .appVertion.compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<AppVersion>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: AppVersion) {
                            if (view != null && isViewBind) {
                                val msg = body.errormsg
                                if (TextUtils.equals(msg, AppConfig.CODE)) {
                                    val base = body.data
                                    if (base != null) {
                                        val ver = base.version
                                        val des = base.description
                                        val url = IP + base.last_android_download_url
                                        if (!TextUtils.equals(ver, getAppVersionName(context))) {
                                            view.mustDown(url, des)
                                        }
                                    }
                                } else {
                                    view?.showErrorMessage(msg)
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                            HandlerError.handlerError(view, e)
                        }
                    })
        }
    }


    /**
     * 返回当前程序版本号
     */
    fun getAppVersionCode(context: Context): String {
        var versioncode = 0
        try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(context.packageName, 0)
            versioncode = pi.versionCode
        } catch (e: Exception) {

        }
        return versioncode.toString() + ""
    }

    /**
     * 返回当前程序版本名
     */
    fun getAppVersionName(context: Context): String? {
        var versionName: String? = null
        try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(context.packageName, 0)
            versionName = pi.versionName
        } catch (e: Exception) {
        }

        return versionName
    }

}