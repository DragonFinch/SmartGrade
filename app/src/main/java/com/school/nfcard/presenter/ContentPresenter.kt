package com.school.nfcard.presenter

import android.text.TextUtils
import com.demo.moddle.presenter.HandlerError
import com.school.nfcard.api.ApiFactory
import com.school.nfcard.entity.*
import com.school.nfcard.presenter.base.BasePresenter
import com.school.nfcard.presenter.http.CallBack
import com.school.nfcard.presenter.http.TransformUtils
import android.content.Context
import com.school.nfcard.presenter.impl.ContentContract
import com.school.nfcard.utils.NetUtils


/**
 *此类的作用：获取联系人
 *
 * Created by Liu on 2018/8/13.
 *
 */
class ContentPresenter(view: ContentContract.View) : BasePresenter<ContentContract.View>(), ContentContract {



    init {
        attachView(view)
    }

    override fun getSchoolContent(context: Context, classId: String) {
        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(classId)) {
            return
        }

        if (isViewBind) {
            ApiFactory.createLogin10Service().getSchoolContent(classId)
                    .compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<SchoolContent>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: SchoolContent) {
                            if (view != null && isViewBind) {
                                val success = body.success
                                val erro = body.errormsg
                                if (!TextUtils.isEmpty(success) && !TextUtils.isEmpty(erro)) {
                                    if (TextUtils.equals(success, "1")) {
                                        if (TextUtils.equals(erro, "0")) {
                                            val dataBean: SchoolContent.DataBean = body.data
                                            if (dataBean != null) {
                                                val content = dataBean.school_content
                                                val url = dataBean.school_img
                                                view.getSchoolSuccess(content, url)
                                            } else {
                                                view.showErrorMessage("网络不好")
                                            }
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
                            HandlerError.handlerError(view, e)
                        }
                    })
        }


    }

    val atmosphereList = ArrayList<String>()
    val mottoList = ArrayList<String>()
    val slogansList = ArrayList<String>()
    val dynaList = ArrayList<Dynamics>()

    override fun getclassContent(context: Context, classId: String) {
        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(classId)) {
            return
        }

        if (isViewBind) {
            ApiFactory.createLogin10Service().getclassContent(classId)
                    .compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<ClassContent>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: ClassContent) {
                            if (view != null && isViewBind) {
                                val success = body.success
                                val erro = body.errormsg
                                if (!TextUtils.isEmpty(success) && !TextUtils.isEmpty(erro)) {
                                    if (TextUtils.equals(success, "1")) {
                                        if (TextUtils.equals(erro, "0")) {
                                            val dataBean: ClassContent.DataBean = body.data
                                            if (dataBean != null) {
                                                val slogans = dataBean.class_slogans
                                                val atmosphere = dataBean.class_atmosphere
                                                val content = dataBean.class_content
                                                val motto = dataBean.class_motto

                                                slogansList.clear()
                                                if (!TextUtils.isEmpty(slogans)) {
                                                    slogansList.addAll(string2List(slogans))
                                                }
                                                view.getSlogansSuccess(slogansList)

                                                mottoList.clear()
                                                mottoList.add("班训:")
                                                if (!TextUtils.isEmpty(motto)) {
                                                    mottoList.addAll(string2List(motto))
                                                }
                                                view.getMottoSuccess(mottoList)
                                                atmosphereList.clear()
                                                atmosphereList.add("班风")
                                                if (!TextUtils.isEmpty(atmosphere)) {
                                                    atmosphereList.addAll(string2List(atmosphere))
                                                }
                                                view.getAtmosphereSuccess(atmosphereList)

                                                if (!TextUtils.isEmpty(content)) {
                                                    view.getClassContentSuccess(content)
                                                } else {
                                                    view.getClassContentSuccess("赶快去填写班级介绍吧")
                                                }
                                            } else {
                                                view.showErrorMessage("网络不好")
                                            }
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
                            HandlerError.handlerError(view, e)
                        }
                    })
        }
    }


    override fun getClassDynamics(context: Context, classId: String) {
        if (!NetUtils.isInternetConnection(context)) {
            view.showErrorMessage("没有网络")
            return
        }

        if (TextUtils.isEmpty(classId)) {
            return
        }

        if (isViewBind) {
            ApiFactory.createLogin10Service().getClassDynamics(classId)
                    .compose(TransformUtils.mainThread())
                    .subscribe(object : CallBack<DynamicsBase>() {
                        override fun beginStart() {

                        }

                        override fun successful(body: DynamicsBase) {
                            if (view != null && isViewBind) {
                                val success = body.success
                                val erro = body.errormsg
                                if (!TextUtils.isEmpty(success) && !TextUtils.isEmpty(erro)) {
                                    if (TextUtils.equals(success, "1")) {
                                        if (TextUtils.equals(erro, "0")) {
                                            dynaList.clear()
                                            dynaList.addAll(body.data)
                                            view.getClassDynamicsSuccess(dynaList)
                                        } else {
                                            view.showErrorMessage("网络不好")
                                        }
                                    } else {
                                        view.showErrorMessage(erro)
                                    }
                                }
                            } else {
                                view.showErrorMessage("网络不好")
                            }
                        }


                        override fun onError(e: Throwable) {
                            HandlerError.handlerError(view, e)
                        }
                    })
        }
    }


    private fun string2List(listText: String): ArrayList<String> {
        val list = ArrayList<String>()
        if (TextUtils.isEmpty(listText)) {
            return list
        }
        if (listText.contains(",")) {
            val tempList = listText.split(",")
            list.addAll(tempList)
        } else {
            list.add(listText)
        }
        return list
    }

}