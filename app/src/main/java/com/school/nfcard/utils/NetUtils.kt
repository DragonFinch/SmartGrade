package com.school.nfcard.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * 此类作用：网络判断工具类
 *
 *
 * 作者：Liu
 *
 *
 */

object NetUtils {
    //监测网络是否连接

    fun  isInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }


}
