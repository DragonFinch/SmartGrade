package com.school.nfcard.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity


/**
 *此类的作用：Fragment 加载工具类
 *
 * Created by Liu on 2018/10/23.
 *
 */
object FragmentUtils {

    fun replace(activity: AppCompatActivity, containerId: Int, fragment: Fragment) {
        val manager: FragmentManager = activity.supportFragmentManager
        try {
            manager.beginTransaction().replace(containerId, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            manager.beginTransaction().replace(containerId, fragment).commitAllowingStateLoss()
        }
    }

}