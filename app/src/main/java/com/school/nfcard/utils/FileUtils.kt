package com.school.nfcard.utils

import java.io.File

/**
 *此类的作用：删除本地文件
 *
 * Created by Liu on 2018/9/18.
 *
 */
object FileUtils {
    fun recurDelete(f: File) {
        try {
            for (fi in f.listFiles()) {
                if (fi.isDirectory) {
                    recurDelete(fi)
                } else {
                    fi.delete()
                }
            }
            f.delete()
        } catch (n: NullPointerException) {
        }
    }
}