package com.school.nfcard.utils

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.Gravity
import android.view.View
import android.view.View.inflate
import android.widget.Toast
import com.bumptech.glide.Glide
import com.school.nfcard.R


/**
 *此类的作用：XXXXXX
 *
 * Created by Liu on 2018/8/14.
 *
 */
class ToastUtils private constructor() {

    private var toast: Toast? = null
    private var textName: AppCompatTextView? = null
    private var imageHead: AppCompatImageView? = null

    companion object {
        private var INSTANCE: ToastUtils? = null
        @Synchronized
        fun getInstance(): ToastUtils {
            if (INSTANCE == null) {
                INSTANCE = ToastUtils()
            }
            return INSTANCE!!
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun showToast(context: Context, name: String, head: String) {
        if (toast == null) {
            toast = Toast(context)
            val view = inflate(context, R.layout.dialog_warning, null)
            toast!!.view = view
            toast!!.setGravity(Gravity.FILL_HORIZONTAL or Gravity.VERTICAL_GRAVITY_MASK, 0, 0)
            toast!!.view.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            textName = view.findViewById(R.id.textName)
            imageHead = view.findViewById(R.id.imageHead)
        }
        if (textName != null && imageHead != null) {
            textName!!.text = name
            Glide.with(context).load(head).into(imageHead!!)
        }
        toast?.show()
    }

}