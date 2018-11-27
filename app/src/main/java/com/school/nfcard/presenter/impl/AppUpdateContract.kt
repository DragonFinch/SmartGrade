package com.school.nfcard.presenter.impl

import android.content.Context
import com.school.nfcard.presenter.base.BaseView

/**
 * 此类作用：用户文件的上传
 *
 *
 * 作者：LiuHW
 */

interface AppUpdateContract {

    fun checkAppVersion(context: Context)

    interface View : BaseView {

        fun mustDown(url: String, description: String)

        fun nextDown(url: String, description: String)

    }
}
