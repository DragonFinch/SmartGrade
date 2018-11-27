package com.school.nfcard

/**
 *此类的作用：XXXXXX
 *
 * Created by Liu on 2018/10/25.
 *
 */
interface Production<out T> {
    fun produce(): T
}

open class Food


open class FastFood : Food()

class Burger : FastFood()