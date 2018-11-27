package com.school.nfcard.ui

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.school.nfcard.R
import com.school.nfcard.entity.SchoolInfo
import com.school.nfcard.entity.help.SchoolHelper
import com.school.nfcard.presenter.impl.LoginContract
import android.graphics.drawable.BitmapDrawable
import android.widget.PopupWindow
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.school.nfcard.entity.ClassInfo
import com.school.nfcard.entity.help.ClassInfoHelper
import com.school.nfcard.presenter.LoginPresenter
import com.school.nfcard.utils.PhoneUtils
import kotlinx.android.synthetic.main.activity_binding.*


/**
 * 此类的作用：XXXXXX
 *
 *
 * Created by Liu on 2018/9/14.
 */
class BindingActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener, SchoolAdapter.OnItemDollColorClickLister, ClassAdapter.OnItemDollColorClickLister {

    var presenter: LoginPresenter? = null

    var w = 0
    var h = 0

    companion object {
        fun jumpTo(context: Context) {
            val intent = Intent(context, BindingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding)
        textSchool.setOnClickListener(this)
        textClass.setOnClickListener(this)
        btnBinding.setOnClickListener(this)
        layoutBindingParent.setOnClickListener(this)
        deviceCode.text = PhoneUtils.getUniqueId(this)
        presenter = LoginPresenter(this)
        val display = this.windowManager.defaultDisplay;
        w = display.width
        h = display.height
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textSchool -> {
                val list: List<SchoolInfo> = SchoolHelper.getSchools()
                showPopupWindow(list)
            }
            R.id.textClass -> {
                if (TextUtils.isEmpty(selectSchoolId)) {
                    showErrorMessage("请先选择学校")
                    return
                }
                val list: List<ClassInfo> = ClassInfoHelper.getClassInfo(selectSchoolId)
                showClassWindow(list)
            }

            R.id.btnBinding -> {
                if (TextUtils.isEmpty(selectClassId)) {
                    showErrorMessage("请选择班级")
                    return
                }
                btnBinding.isClickable = false
                presenter?.bindingDevice(this, selectSchoolId!!, selectClassId!!, PhoneUtils.getUniqueId(this))
            }

            R.id.layoutBindingParent -> {
                val imm =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if(imm.isActive && currentFocus !=null){
                    if (currentFocus.windowToken !=null) {
                        imm.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter?.detachView()
            presenter = null
        }
    }


    override fun showErrorMessage(message: String) {
        btnBinding.isClickable = true
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun bindingSuccess() {
        this.finish()
    }

    private var mPopWindow: PopupWindow? = null

    private fun showPopupWindow(dollColors: List<SchoolInfo>) {
        if (dollColors.isNotEmpty()) {
            val contentView = LayoutInflater.from(this).inflate(R.layout.dialog_recycleview, null)
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val recyclerView = contentView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = layoutManager
            val adapter = SchoolAdapter(this, dollColors)
            recyclerView.adapter = adapter
            mPopWindow = PopupWindow(contentView, w,h, true)
            mPopWindow?.contentView = contentView
            mPopWindow?.setBackgroundDrawable(BitmapDrawable())
            mPopWindow?.isOutsideTouchable = true
            mPopWindow?.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)
            adapter.setColorClickLister(this)
        }
    }

    private var selectSchoolId: String? = ""
    private var selectClassId: String? = ""
    override fun onItemClick(bean: SchoolInfo) {
        mPopWindow?.dismiss()
        textSchool.text = bean.name
        selectSchoolId = bean.id
    }


    private var classWindow: PopupWindow? = null
    private fun showClassWindow(dollColors: List<ClassInfo>) {
        if (dollColors.isNotEmpty()) {
            val contentView = LayoutInflater.from(this).inflate(R.layout.dialog_recycleview, null)
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val recyclerView = contentView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = layoutManager
            val adapter = ClassAdapter(this, dollColors)
            recyclerView.adapter = adapter
            classWindow = PopupWindow(contentView, w, h, true)
            classWindow?.contentView = contentView
            classWindow?.setBackgroundDrawable(BitmapDrawable())
            classWindow?.isOutsideTouchable = true
            classWindow?.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)
            adapter.setColorClickLister(this)
        }
    }


    override fun onItemClick(colorBean: ClassInfo) {
        classWindow?.dismiss()
        textClass.text = colorBean.name
        selectClassId = colorBean.id
    }


    override fun loginSuccess() {

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }


}
