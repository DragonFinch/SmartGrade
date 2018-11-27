package com.demo.moddle.presenter

import android.text.TextUtils
import com.demo.moddle.utils.ResUtils
import com.school.nfcard.R
import com.school.nfcard.presenter.base.BaseView

/**
 *此类的作用：处理返回的错误信息
 *
 * Created by Liu on 2018/6/23.
 *
 */
object HandlerError {
    fun handlerError(view: BaseView?, e: Throwable) {
        var error: String? = e.message
        when {
            TextUtils.isEmpty(error) -> error = ResUtils.getString(R.string.HTTPServiceException)
            error?.endsWith(ResUtils.getString(R.string.HTTPNoAddress))!! -> error = ResUtils.getString(R.string.HTTPServiceException)
            TextUtils.equals(error, ResUtils.getString(R.string.HTTP404)) -> error = ResUtils.getString(R.string.HTTPServiceException)
            error.startsWith(ResUtils.getString(R.string.HTTPTimeout)) -> error = ResUtils.getString(R.string.timeout)
            error.startsWith(ResUtils.getString(R.string.HTTPFAILED)) -> error = ResUtils.getString(R.string.HTTPServiceException)
            error.startsWith(ResUtils.getString(R.string.HTTP500)) -> error = ResUtils.getString(R.string.HTTPServiceException)
            error.contains(ResUtils.getString(R.string.JsonObjectError)) -> error = ResUtils.getString(R.string.JsonError)
            error.contains(ResUtils.getString(R.string.JsonArrayError)) -> error = ResUtils.getString(R.string.JsonError)
        }
        if (view != null) {
            view.hideLoading()
            if (error != null) {
                view.showErrorMessage(error)
            }
        }
    }

}