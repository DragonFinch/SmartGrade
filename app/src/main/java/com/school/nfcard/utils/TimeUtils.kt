package com.school.nfcard.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 *此类的作用：时间操作工具类
 *
 * Created by Liu on 2018/8/14.
 *
 */
object TimeUtils {

    private const val minute = (60 * 1000).toLong()// 1分钟
    private const val hour = 60 * minute// 1小时
    private const val day = 24 * hour// 1天
    private const val month = 31 * day// 月
    private const val year = 12 * month// 年

    /**
     * @param @return 设定文件
     * @return Long 返回类型
     * @throws
     * @Title: getCurrentSystemTime
     * @Description: 获取当前系统时间
     */
    fun getCurrentSystemTime(): Long {
        return Calendar.getInstance().timeInMillis
    }

    /**
     * 取出当前的日期格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    fun getCurrentTimeStr(): String {
        val curCalendar = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return df.format(curCalendar.time)
    }


    fun getCurrentTimeYYMMDD(): String {
        val curCalendar = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(curCalendar.time)
    }

    fun getCurrentTimeMMDD(): String {
        val curCalendar = Calendar.getInstance()
        val df = SimpleDateFormat("MMdd")
        return df.format(curCalendar.time)
    }


    /**
     * 将时间戳转成格式化时间
     *
     * @param milliseconds
     */
    fun getFormatTime(milliseconds: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        return sdf.format(Date(milliseconds))
    }

    /**
     * 将时间戳转成格式化时间
     *
     * @param milliseconds
     */
    fun getFormatTimeYY(milliseconds: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date(milliseconds))
    }


    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    fun getTimeFormatText(date: Long): String {
        var date = date
        if (date == 0L) {
            return ""
        }
        date *= 1000
        val diff = Date().time - date
        var r: Long = 0
        if (diff > year) {
            r = diff / year
            return r.toString() + "年前"
        }
        if (diff > month) {
            r = diff / month
            return r.toString() + "月前"
        }
        if (diff > day) {
            r = diff / day
            return r.toString() + "天前"
        }
        if (diff > hour) {
            r = diff / hour
            return r.toString() + "小时前"
        }
        if (diff > minute) {
            r = diff / minute
            return r.toString() + "分钟前"
        }
        return "刚刚"
    }

    /**
     * 将时间戳转成格式化时间
     *
     * @param milliseconds
     */
    fun getFormatTimeHHMM(milliseconds: Long): String {
        val sdf = SimpleDateFormat("hh:mm:ss")
        return sdf.format(Date(milliseconds))
    }


    /**
     * 将时间戳转成格式化时间
     *
     * @param milliseconds
     */
    fun getFormatTime24(milliseconds: Long): String {
        val sdf = SimpleDateFormat("kk:mm")
        return sdf.format(Date(milliseconds))
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun daysBetween(smdate: Date, bdate: Date): Int {
        var smdate = smdate
        var bdate = bdate
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        smdate = sdf.parse(sdf.format(smdate))
        bdate = sdf.parse(sdf.format(bdate))
        val cal = Calendar.getInstance()
        cal.time = smdate
        val time1 = cal.timeInMillis
        cal.time = bdate
        val time2 = cal.timeInMillis
        val betweenDays = (time2 - time1) / (1000 * 3600 * 24)
        return Integer.parseInt(betweenDays.toString())
    }

    /**
     * 字符串的日期格式的计算
     */
    @Throws(ParseException::class)
    fun daysBetween(smdate: String, bdate: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        cal.time = sdf.parse(smdate)
        val time1 = cal.timeInMillis
        cal.time = sdf.parse(bdate)
        val time2 = cal.timeInMillis
        val betweenDays = (time2 - time1) / (1000 * 3600 * 24)
        return Integer.parseInt(betweenDays.toString())
    }

    /**
     * 截取Date(1418784200000-0000)的固定长度
     */
    fun spliteTime(dateStr: String): String {
        val sequence = dateStr.replace("/Date(", "")
        return sequence.substring(0, 13).trim { it <= ' ' }
    }

    /**
     * 获取分秒格式化字符串
     *
     * @param duration
     * @return
     */
    fun getFormatMiniteSecString(duration: Int): String {
        val minutes = duration % (60 * 60) / 60//分钟时长
        val seconds = duration % 60//秒时长
        return String.format("%d'%d''", minutes, seconds)
    }

    /**
     * 格式化时间字符串
     *
     *
     * 显示规则大于1天,显示天.  大于1小时,显示1=小时.   大于1分钟, 显示分钟
     * 其中,大于7天以上的均显示7天前
     *
     * @param time
     * @return
     */
    fun getFormatTimeString(time: Long): String {
        val currentTime = Date().time / 1000//获得当前时间
        val diffTime = currentTime - time//当前时间减去创建时间,得到时间差
        val diffDay = diffTime / (24 * 3600) //得到天数
        val diffHour = diffTime % (24 * 3600) / 3600 //得到小时数
        val diffMinute = diffTime % 3600 / 60 //得到分钟数
        return if (diffDay >= 1) {
            if (diffDay >= 7) {
                "7天前"
            } else {
                diffDay.toString() + "天前"
            }
        } else if (diffHour >= 1) {
            diffHour.toString() + "小时前"
        } else if (diffMinute >= 1) {
            diffMinute.toString() + "分钟前"
        } else {
            ""
        }
    }


}