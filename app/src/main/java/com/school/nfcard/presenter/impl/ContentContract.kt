package com.school.nfcard.presenter.impl

import android.content.Context
import com.school.nfcard.entity.Dynamics
import com.school.nfcard.entity.Student
import com.school.nfcard.presenter.base.BaseView

/**
 * 此类作用：用户文件的上传
 *
 *
 * 作者：LiuHW
 */

interface ContentContract {

    fun getSchoolContent(context: Context, classId: String)

    fun getclassContent(context: Context, classId: String)


    fun getClassDynamics(context: Context, classId: String)

    interface View : BaseView {

        fun getSchoolSuccess(content: String, url: String)

        fun getAtmosphereSuccess(atmosphereList: List<String>)

        fun getMottoSuccess(mottoList: List<String>)

        fun getSlogansSuccess(slogansList: List<String>)

        fun getClassContentSuccess(classContent: String)


        fun getClassDynamicsSuccess(dynaList: List<Dynamics>)
    }
}
