package com.school.nfcard.utils

import android.content.Context
import android.os.Build
import android.provider.Settings
import java.io.IOException
import java.io.InputStreamReader
import java.io.LineNumberReader

/**
 * 此类的作用：XXXXXX
 *
 *
 * Created by Liu on 2018/9/13.
 */
object PhoneUtils {

    /*****
     * 获取手机的唯一标识
     *
     * @param context
     * @return
     */
    fun getUnique(context: Context): String {
        val androidID = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return androidID + Build.SERIAL
    }


    fun getUniqueId(context: Context): String {
        var macSerial: String = ""
        var str: String? = ""
        try {
            val pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ")
            val ir = InputStreamReader(pp.inputStream)
            val input = LineNumberReader(ir)
            while (null != str) {
                str = input.readLine()
                if (str != null) {
                    macSerial = str.trim { it <= ' ' }
                    macSerial = macSerial.replace(":", "")
                    break
                }
            }
        } catch (ex: IOException) {
            // 赋予默认值
            ex.printStackTrace()
        }

        return macSerial
    }


}
