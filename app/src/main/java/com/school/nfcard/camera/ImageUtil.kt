package com.school.nfcard.camera

/**
 * 此类的作用：XXXXXX
 *
 *
 * Created by Liu on 2018/8/18.
 */
import android.graphics.Bitmap
import android.graphics.Matrix

object ImageUtil {
    /**
     * 旋转Bitmap
     * @param b
     * @param rotateDegree
     * @return
     */
    fun getRotateBitmap(b: Bitmap, rotateDegree: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotateDegree)
        return Bitmap.createBitmap(b, 0, 0, b.width, b.height, matrix, false)
    }
}

