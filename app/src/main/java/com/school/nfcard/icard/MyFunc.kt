package com.school.nfcard.icard


object MyFunc {
    fun isOdd(num: Int): Int {
        return num and 0x1
    }

    fun hexToInt(inHex: String): Int {
        return Integer.parseInt(inHex, 16)
    }

    fun hexToByte(inHex: String): Byte {
        return Integer.parseInt(inHex, 16).toByte()
    }


    fun byte2Hex(inByte: Byte?): String {
        return String.format("%02x", inByte).toUpperCase()
    }

    fun byteArrToHex(inBytArr: ByteArray): String {
        val strBuilder = StringBuilder()
        val j = inBytArr.size
        for (i in 0 until j) {
            strBuilder.append(byte2Hex(inBytArr[i]))
            strBuilder.append("")
        }
        return strBuilder.toString()
    }

    fun byteArrToHex(inBytArr: ByteArray, offset: Int, byteCount: Int): String {
        val strBuilder = StringBuilder()
        for (i in offset until byteCount) {
            strBuilder.append(byte2Hex(inBytArr[i]))
        }
        return strBuilder.toString()
    }

    fun hexToByteArr(inHex: String): ByteArray {
        var inHex = inHex
        var hexlen = inHex.length
        val result: ByteArray
        if (isOdd(hexlen) == 1) {
            hexlen++
            result = ByteArray(hexlen / 2)
            inHex = "0$inHex"
        } else {
            result = ByteArray(hexlen / 2)
        }
        var j = 0
        var i = 0
        while (i < hexlen) {
            result[j] = hexToByte(inHex.substring(i, i + 2))
            j++
            i += 2
        }
        return result
    }
}