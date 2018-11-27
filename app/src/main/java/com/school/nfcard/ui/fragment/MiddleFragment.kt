package com.school.nfcard.ui.fragment

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.school.nfcard.HomeActivity
import com.school.nfcard.R
import com.school.nfcard.entity.ClassInfo
import com.school.nfcard.entity.Dynamics
import com.school.nfcard.entity.Lesson
import com.school.nfcard.entity.SchoolInfo
import com.school.nfcard.entity.help.SchoolHelper
import com.school.nfcard.presenter.ContentPresenter
import com.school.nfcard.presenter.impl.ContentContract
import com.school.nfcard.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_middle.*


/**
 *此类的作用：校园风采
 *
 * Created by Liu on 2018/10/23.
 *
 */
class MiddleFragment : Fragment(), ContentContract.View {


    private var contentPresenter: ContentPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragment_middle, null)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentPresenter = ContentPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        val homeActivity = activity as HomeActivity
        val schoolBean: Lesson.DataBean.EquipmentBean = homeActivity.schoolBean
        val name = schoolBean.school
        val classId = schoolBean.classid
        if (!TextUtils.isEmpty(name)) {
            textFragmentMiddleName.text = name
        }
        if (!TextUtils.isEmpty(classId)) {
            contentPresenter?.getSchoolContent(context!!, classId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (contentPresenter != null) {
            contentPresenter?.detachView()
            contentPresenter = null
        }
    }

    override fun showErrorMessage(message: String?) {
        ToastUtil.getInstance().showToast(context, message)
    }

    override fun getSchoolSuccess(content: String, url: String) {
        this.textFragmentMiddleContent.text = content
        Glide.with(context!!).load(url).into(imageFragmentMiddleContent)
    }


    //===================================下面的没有用到==============================================================
    override fun getAtmosphereSuccess(atmosphereList: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMottoSuccess(mottoList: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSlogansSuccess(slogansList: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClassContentSuccess(classContent: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClassDynamicsSuccess(dynaList: List<Dynamics>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}