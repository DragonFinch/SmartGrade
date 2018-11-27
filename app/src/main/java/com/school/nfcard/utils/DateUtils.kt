package com.school.nfcard.utils

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 *此类的作用：时间判断工具类
 *
 * Created by Liu on 2018/8/20.
 *
 */
object DateUtils {


    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    private fun belongCalendar(nowTime: Date, beginTime: Date, endTime: Date): Boolean {
        return nowTime.time >= beginTime.time && nowTime.time <= endTime.time
    }


    private fun belongAM(nowTime: Date, endTime: Date): Boolean {
        return nowTime.time <= endTime.time
    }

    private fun belongPM(nowTime: Date, beginTime: Date): Boolean {
        return nowTime.time >= beginTime.time
    }

    fun isBelong(beginTime: String, endTime: String): String {
        val df = SimpleDateFormat("HH:mm", Locale.CHINA)
        var now = Date()
        var beginTime = Date()
        var endTime = Date()
        try {
            now = df.parse(df.format(Date()))
            beginTime = df.parse(beginTime.toString())
            endTime = df.parse(endTime.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val flag = belongCalendar(now, beginTime, endTime)
        if (flag) {
            return "work"
        } else {
            if (belongAM(now, beginTime)) {
                return "am"
            }
            if (belongPM(now, endTime)) {
                return "pm"
            }
        }
        return "work"
    }


    fun getDayOfWeek(): Int {
        val dayNames = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        val calendar = Calendar.getInstance()
        val date = Date()
        calendar.time = date
        var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (dayOfWeek <= 0) dayOfWeek = 7
        return dayOfWeek
    }


    fun dateToStamp(s: String): Long {
        var lng: Long = -1L
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = simpleDateFormat.parse(s)
            lng = date.time
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return lng
    }


    fun getDate(): String {
        val millis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(millis)
        return sdf.format(date)
    }


}