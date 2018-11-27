package com.school.nfcard.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 *此类的作用：XXXXXX
 *
 * Created by Liu on 2018/9/13.
 *
 */
class RestartService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}