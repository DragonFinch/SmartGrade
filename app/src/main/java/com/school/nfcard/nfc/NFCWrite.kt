package com.school.nfcard.nfc

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef

import java.nio.charset.Charset
import java.util.Locale

/**
 * 此类的作用：NFC  写标签
 *
 *
 * Created by Liu on 2018/8/10.
 */
object NFCWrite {

    /**
     * 创建NDEF文本数据
     *
     * @param text
     * @return
     */
    fun createTextRecord(text: String): NdefRecord {
        val langBytes = Locale.CHINA.language.toByteArray(Charset.forName("US-ASCII"))
        val utfEncoding = Charset.forName("UTF-8")
        //将文本转换为UTF-8格式
        val textBytes = text.toByteArray(utfEncoding)
        //设置状态字节编码最高位数为0
        val utfBit = 0
        //定义状态字节
        val status = (utfBit + langBytes.size).toChar()
        val data = ByteArray(1 + langBytes.size + textBytes.size)
        //设置第一个状态字节，先将状态码转换成字节
        data[0] = status.toByte()
        //设置语言编码，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1到langBytes.length的位置
        System.arraycopy(langBytes, 0, data, 1, langBytes.size)
        //设置文本字节，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1 + langBytes.length
        //到textBytes.length的位置
        System.arraycopy(textBytes, 0, data, 1 + langBytes.size, textBytes.size)
        //通过字节传入NdefRecord对象
        //NdefRecord.RTD_TEXT：传入类型 读写
        return NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, ByteArray(0), data)
    }

    /**
     * 写数据
     *
     * @param ndefMessage 创建好的NDEF文本数据
     * @param tag         标签
     * @return
     */
    fun writeTag(ndefMessage: NdefMessage, tag: Tag): Boolean {
        try {
            val ndef = Ndef.get(tag)
            ndef.connect()
            ndef.writeNdefMessage(ndefMessage)
            return true
        } catch (e: Exception) {
        }

        return false
    }


}
