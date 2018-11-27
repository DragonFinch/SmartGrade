package com.school.nfcard.utils

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool

import com.school.nfcard.R

import java.util.HashMap


/**
 * 此类的作用：XXXXXX
 *
 *
 * Created by Liu on 2018/8/11.
 */
object SoundManager {
    private var soundPool: SoundPool? = null
    private var context: Context? = null
    private var soundMap: MutableMap<Int, Int>? = null
    private val opMusic: String? = null

    fun init(c: Context) {
        context = c
        initSound()
    }

    private fun initSound() {
        if (soundPool == null) {
            soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 100)
        }
        soundMap = HashMap()
        soundMap!![R.raw.game_start] = soundPool!!.load(context, R.raw.game_start, 1)
        soundMap!![R.raw.game_faile] = soundPool!!.load(context, R.raw.game_faile, 1)
        soundMap!![R.raw.game_success] = soundPool!!.load(context, R.raw.game_success, 1)
    }


    private fun playSound(resId: Int) {
        val soundId = soundMap!![resId]
        if (soundId != null) {
            soundPool!!.play(soundId, 1f, 1f, 1, 0, 1f)
        }
    }


    /****
     * 开始游戏的声音
     */
    fun startSound() {
        playSound(R.raw.game_start)
    }

    /***
     *
     * 抓取失败的声音
     */
    fun failedSound() {
        playSound(R.raw.game_faile)
    }

    /***
     *
     * 抓取成功的声音
     */
    fun successSound() {
        playSound(R.raw.game_success)
    }


    fun release() {
        if (soundPool != null) {
            soundPool!!.unload(R.raw.game_start)
            soundPool!!.unload(R.raw.game_faile)
            soundPool!!.unload(R.raw.game_success)
            soundPool!!.release()
            soundPool = null
        }
    }
}
