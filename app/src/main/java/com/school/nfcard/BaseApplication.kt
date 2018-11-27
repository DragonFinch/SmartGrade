package com.school.nfcard

import android.app.Application
import android.content.Context
import com.demo.moddle.utils.ResUtils
import com.school.nfcard.entity.help.GreenDaoManager
import com.school.nfcard.utils.CrashHandler

/**
 * 此类作用：Application的基类
 *
 * 作者：LiuHW
 */

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
       // CrashHandler.getInstance().init(this)
        instance = this
        context = applicationContext
        init()
    }

    /***
     * 各种工具的初始化信息
     */
    private fun init() {
        context?.let { ResUtils.initContext(it) }
        GreenDaoManager.getInstance()
    }

    companion object {
         var instance = BaseApplication()
            private set
         var context: Context? = null
            private set
    }
}
