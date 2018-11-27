package com.school.nfcard.presenter

import android.content.Context
import android.text.TextUtils
import com.demo.moddle.presenter.HandlerError
import com.school.nfcard.api.ApiFactory
import com.school.nfcard.constant.AppConfig
import com.school.nfcard.entity.*
import com.school.nfcard.entity.help.StudentHelper
import com.school.nfcard.presenter.base.BasePresenter
import com.school.nfcard.presenter.http.CallBack
import com.school.nfcard.presenter.http.TransformUtils
import com.school.nfcard.presenter.impl.GetContactContract
import com.school.nfcard.ui.card.CardEvent
import com.school.nfcard.utils.NetUtils
import com.school.nfcard.utils.ToastUtils
import java.util.ArrayList

/**
 *此类的作用：获取联系人
 *
 * Created by Liu on 2018/8/13.
 *
 */
class GetContactPresenter(view: GetContactContract.View) : BasePresenter<GetContactContract.View>(), GetContactContract {



    init {
        attachView(view)
    }


    override fun swipCard(context: Context, idcard: String, head: String) {
        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(idcard)) {
            return
        }
        if (isViewBind) {
            ApiFactory.createLogin10Service().swipecard(idcard, head)
                    .compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<SwipeCard>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: SwipeCard) {
                            if (view != null && isViewBind) {
                                val success = body.success
                                val erro = body.errormsg
                                if (!TextUtils.isEmpty(success)) {
                                    val swipeCard = body.data
                                    view.swipCardSuccess(swipeCard)
                                } else {
                                    if (!TextUtils.isEmpty(erro)) {
                                        view.bindingCard(erro)
                                    }
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                            HandlerError.handlerError(view, e)
                        }
                    })
        }


    }

    val lessonList = ArrayList<String>()
    /****
     * 获取课程表
     */
    override fun getLesson(context: Context, equipment: String) {

        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(equipment)) {
            return
        }

        if (isViewBind) {
            ApiFactory.createLogin10Service().getLesson(equipment)
                    .compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<Lesson>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: Lesson) {
                            if (view != null && isViewBind) {
                                val success = body.success
                                val erro = body.errormsg
                                if (!TextUtils.isEmpty(success)) {
                                    lessonList.clear()
                                    val dateBean: Lesson.DataBean = body.data
                                    val schoolname = dateBean.equipment.school
                                    val clazzName = dateBean.equipment.classX
                                    val classId = dateBean.equipment.classid
                                    val lesson = dateBean.lesson
                                    lessonList.add("节/周")
                                    lessonList.add("周一")
                                    lessonList.add("周二")
                                    lessonList.add("周三")
                                    lessonList.add("周四")
                                    lessonList.add("周五")
                                    lessonList.add("周六")
                                    lessonList.add("周日")
                                    for (item in lesson) {
                                        lessonList.add(item.lesson)
                                    }
                                    view.getLessones(schoolname, clazzName, classId, lessonList)
                                    view.getSchoolInfo(dateBean.equipment)
                                } else {
                                    if (!TextUtils.isEmpty(erro)) {
                                        view.bindingDevice(erro)
                                    }
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                            HandlerError.handlerError(view, e)
                        }
                    })
        }
    }

    private val list = ArrayList<Student>()


    /*****
     * 根据设备的ID查询此机子的班级信息（学校的名字，班级的名字，学生有哪些）
     */

    override fun getContact(context: Context, cToken: String, type: String) {

        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }
        if (TextUtils.isEmpty(cToken)) {
            return
        }
        if (TextUtils.isEmpty(type)) {
            return
        }
        if (isViewBind) {
            list.clear()
            ApiFactory.createApiService().getContact(cToken, type)
                    .compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<BaseListModle<Student>>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: BaseListModle<Student>) {
                            if (view != null && isViewBind) {
                                val msg = body.errormsg
                                if (TextUtils.equals(msg, AppConfig.CODE)) {
                                    val base = body.data
                                    if (base != null) {
                                        StudentHelper.deleteAll()
                                        list.addAll(base)
                                        for (student: Student in list) {
                                            StudentHelper.insert(student)
                                        }
                                    }
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                        }
                    })
        }
    }


    /******
     * 给父母发通知
     *
     *
     * 这个方法不用了
     */
    override fun sendToParent(context: Context, cToken: String, studentid: String, student_name: String, comeout: String, parents: String, logo: String) {

        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(cToken)) {
            return
        }
        if (TextUtils.isEmpty(studentid)) {
            return
        }
        if (TextUtils.isEmpty(student_name)) {
            return
        }
        if (TextUtils.isEmpty(comeout)) {
            return
        }
        if (TextUtils.isEmpty(parents)) {
            return
        }
        if (TextUtils.isEmpty(logo)) {
            return
        }
        ApiFactory.createApiService().inoutschool(cToken, studentid, student_name, comeout, parents, logo)
                .compose(TransformUtils.mainThread())
                .subscribe(object : CallBack<BaseModle>() {
                    override fun beginStart() {

                    }

                    override fun successful(body: BaseModle) {
                        if (view != null && isViewBind) {
                            val msg = body.errormsg
                            if (TextUtils.equals(msg, AppConfig.CODE)) {
                                view?.sendToParentSuccess()
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    private val cardList = ArrayList<CardEvent>()


    override fun getClassEvent(context: Context, classId: String) {
        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(classId)) {
            return
        }

        ApiFactory.createLogin10Service().getClassEvent(classId)
                .compose(TransformUtils.mainThread())
                .subscribe(object : CallBack<ClassEvent>() {
                    override fun beginStart() {

                    }

                    override fun successful(body: ClassEvent) {
                        if (view != null && isViewBind) {
                            val success = body.success
                            val erro = body.errormsg
                            if (!TextUtils.isEmpty(success) && !TextUtils.isEmpty(erro)) {
                                if (TextUtils.equals(success, "1")) {
                                    if (TextUtils.equals(erro, "0")) {
                                        cardList.clear()
                                        cardList.addAll(body.data)
                                        view.getDynaSuccess(cardList)
                                    } else {
                                        view.showErrorMessage(erro)
                                    }
                                }
                            } else {
                                view.showErrorMessage("网络不好")
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                })

    }


}