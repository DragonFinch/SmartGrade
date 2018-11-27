package com.demo.moddle.utils

/**
 *此类的作用：资源文件管理工具类
 *
 * Created by Liu on 2018/6/8.
 *
 */
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getDrawable
import android.util.DisplayMetrics
import android.view.WindowManager

class ResUtils  constructor() {

    init {
        throw IllegalAccessError("Please init this class at BaseApplication")
    }

    companion object {

        private var sContext: Context? = null

        fun initContext(context: Context) {
            sContext = context
        }

        fun getString(stringId: Int): String {
            return sContext!!.resources.getString(stringId)
        }

        fun getString(stringId: Int, param: Int): String {
            return sContext!!.resources.getString(stringId, param)
        }

        fun getInt(intId: Int): Int {
            return sContext!!.resources.getInteger(intId)
        }

        fun getStringArray(stringArrayId: Int): Array<String> {
            return sContext!!.resources.getStringArray(stringArrayId)
        }

        fun getIntArray(intArrayId: Int): IntArray {

            return sContext!!.resources.getIntArray(intArrayId)
        }

        fun getColor(colorId: Int): Int {
            return ContextCompat.getColor(this.sContext!!, colorId)
        }

        fun getDimension(dimensionId: Int): Float {
            return sContext!!.resources.getDimension(dimensionId)
        }

        val isPortOrientation: Boolean
            get() = sContext!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        /*
        0.75 - ldpi
        1.0 - mdpi
        1.5 - hdpi
        2.0 - xhdpi
        3.0 - xxhdpi
        4.0 - xxxhdpi
     */
        val densityKoef: Float
            get() = sContext!!.resources.displayMetrics.density

        //DisplayMetrics.DENSITY_XXXHIGH;
        //DisplayMetrics.DENSITY_XXHIGH;
        val density: Int
            get() {
                val density = densityKoef
                if (density >= 4.0) {
                    return 640
                }
                if (density >= 3.0) {
                    return 480
                }
                if (density >= 2.0) {
                    return DisplayMetrics.DENSITY_XHIGH
                }
                if (density >= 1.5) {
                    return DisplayMetrics.DENSITY_HIGH
                }
                if (density >= 1.0) {
                    return DisplayMetrics.DENSITY_MEDIUM
                }
                return if (density >= 0.75) {
                    DisplayMetrics.DENSITY_LOW
                } else DisplayMetrics.DENSITY_MEDIUM
            }


        fun dpFromPx(px: Int): Int {
            return (px / sContext!!.resources.displayMetrics.density).toInt()
        }

        fun pxFromDp(dp: Int): Int {
            return (dp * sContext!!.resources.displayMetrics.density).toInt()
        }

        fun getDrawable(drawableID: Int): Drawable? {
            val drawable = getDrawable(this.sContext!!, drawableID)
            //必须设置图片大小，否则不显示
            drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            return drawable
        }

        val screenWH: IntArray
            get() {
                val wh = IntArray(2)
                val wm = sContext!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                var size = Point()
                wm.defaultDisplay.getSize(size)
                wh[0] = size.x
                wh[1] = size.y
                return wh
            }
    }


}
