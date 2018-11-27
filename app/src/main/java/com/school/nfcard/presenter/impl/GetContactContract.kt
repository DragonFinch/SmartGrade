package com.school.nfcard.presenter.impl

import android.content.Context
import com.school.nfcard.entity.Lesson
import com.school.nfcard.entity.Student
import com.school.nfcard.entity.SwipeCard
import com.school.nfcard.presenter.base.BaseView
import com.school.nfcard.ui.card.CardEvent


/**
 *
 * Created by Liu on 2017/10/23.
 */

interface GetContactContract {
    /****
     * 获取联系方式
     *
     * @param context context
     */
    fun getContact(context: Context, cToken: String, type: String)

    fun sendToParent(context: Context, cToken: String, studentid: String, student_name: String, comeout: String, parents: String, logo: String)

    fun getLesson(context: Context, equipment: String)

    fun swipCard(context: Context, idcard: String, head: String)


    fun getClassEvent(context: Context, classId: String)

    interface View : BaseView {

        fun getContactSuccess(list: List<Student>)

        fun sendToParentSuccess()

        fun getLessones(schoolName: String, className: String, classId: String, list: List<String>)

        fun bindingCard(erro: String)

        fun bindingDevice(erro: String)

        fun swipCardSuccess(swipeCard: SwipeCard.DataBean)

        fun getSchoolInfo(schoolBean: Lesson.DataBean.EquipmentBean)

        fun getDynaSuccess(cardList: List<CardEvent>)
    }
}
