package com.school.nfcard.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.school.nfcard.HomeActivity

import com.school.nfcard.MainActivity

/**
 * 此类的作用：开机自启动广播
 *
 *
 * Created by Liu on 2018/8/21.
 */
class ContentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val it = Intent(context, HomeActivity::class.java)
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(it)
    }
}
