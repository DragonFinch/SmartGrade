package com.school.nfcard.nfc

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag


/**
 * 此类的作用：读取NFC标签的卡号
 *
 *
 * Created by Liu on 2018/8/10.
 */
object NFCRead {
    /**
     * 读取NFC标签文本数据
     */
    fun readNfcTag(intent: Intent): String {
        try {
            if (NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
                val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                val tagId = tag.id
                var str = byteArrayToHexString(tagId)
                str = flipHexStr(str)
                return str
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "刷卡错误"
    }

    private fun flipHexStr(s: String): String {
        val result = StringBuilder()
        var i = 0
        while (i <= s.length - 2) {
            result.append(StringBuilder(s.substring(i, i + 2)).reverse())
            i += 2
        }
        return result.reverse().toString()
    }


    private fun byteArrayToHexString(inarray: ByteArray): String {
        var i: Int
        var j: Int = 0
        var `in`: Int
        val hex = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var out = ""
        while (j < inarray.size) {
            `in` = inarray[j].toInt() and 0xff
            i = `in` shr 4 and 0x0f
            out += hex[i]
            i = `in` and 0x0f
            out += hex[i]
            ++j
        }
        return out
    }
}
