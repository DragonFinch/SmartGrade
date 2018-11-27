package com.school.nfcard.nfc

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.provider.Settings

/**
 *此类的作用：NFC 操作工具类
 *
 * Created by Liu on 2018/8/13.
 *
 */
class NFCUtils(activity: Activity) {

    init {
        mNfcAdapter = checkNFC(activity)
        initNFC(activity)
    }

    companion object {
        var mNfcAdapter: NfcAdapter? = null
        var mIntentFilter: Array<IntentFilter>? = null
        var mPendingIntent: PendingIntent? = null
        var mTechList: Array<Array<String>>? = null


                /**
         * 检查NFC是否打开
         */
        fun checkNFC(activity: Activity): NfcAdapter? {
            val mNfcAdapter = NfcAdapter.getDefaultAdapter(activity)
            if (mNfcAdapter == null) {
                return null
            } else {
                if (!mNfcAdapter.isEnabled) {
                    val setNfc = Intent(Settings.ACTION_NFC_SETTINGS)
                    activity.startActivity(setNfc)
                }
            }
            return mNfcAdapter
        }

        /**
         * 初始化nfc设置
         */
        fun initNFC(activity: Activity) {
            mPendingIntent = PendingIntent.getActivity(activity, 0, Intent(activity, activity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
            val filter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
            val filter2 = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
            try {
                filter.addDataType("*/*")
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                e.printStackTrace()
            }
            mIntentFilter = arrayOf(filter, filter2)
            mTechList = null
        }
    }
}