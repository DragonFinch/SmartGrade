package com.school.nfcard.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import com.school.nfcard.HomeActivity
import com.school.nfcard.R
import com.school.nfcard.adapter.AtmosphereAdapter
import com.school.nfcard.adapter.DynamicsAdapter
import com.school.nfcard.adapter.MottoAdapter
import com.school.nfcard.adapter.SlogansAdapter
import com.school.nfcard.entity.ClassInfo
import com.school.nfcard.entity.Dynamics
import com.school.nfcard.entity.Lesson
import com.school.nfcard.entity.help.SchoolHelper
import com.school.nfcard.presenter.ContentPresenter
import com.school.nfcard.presenter.impl.ContentContract
import com.school.nfcard.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_right.*
import com.school.nfcard.widget.DividerGridView
import kotlinx.android.synthetic.main.fragment_middle.*


/**
 *此类的作用：班级信息
 *
 * Created by Liu on 2018/10/23.
 *
 */
class RightFragment : Fragment(), ContentContract.View, Chronometer.OnChronometerTickListener {


    private var contentPresenter: ContentPresenter? = null


    val atmosphereList = ArrayList<String>()
    val mottoList = ArrayList<String>()
    val slogansList = ArrayList<String>()
    val dynaList = ArrayList<Dynamics>()

    var atmoAdapter: AtmosphereAdapter? = null
    var mottoAdapter: MottoAdapter? = null
    var slogAdapter: SlogansAdapter? = null
    var dynaAdapter: DynamicsAdapter? = null

    var classId = ""
    var className = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragment_right, null)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentPresenter = ContentPresenter(this)

        atmoAdapter = AtmosphereAdapter(context!!, atmosphereList)
        val atmoManager = GridLayoutManager(context!!, 5)
        listRightClassAtmos.addItemDecoration(DividerGridView(R.dimen.grid_padding, R.color.tran))
        listRightClassAtmos.layoutManager = atmoManager
        listRightClassAtmos.adapter = atmoAdapter

        mottoAdapter = MottoAdapter(context!!, mottoList)
        val mottoManager = GridLayoutManager(context!!, 5)
        listRightClassMotto.addItemDecoration(DividerGridView(R.dimen.grid_padding_10, R.color.tran))
        listRightClassMotto.layoutManager = mottoManager
        listRightClassMotto.adapter = mottoAdapter


        slogAdapter = SlogansAdapter(context!!, slogansList)
        val slogManager = GridLayoutManager(context!!, 1)
        listRightClassSlogans.layoutManager = slogManager
        listRightClassSlogans.adapter = slogAdapter


        dynaAdapter = DynamicsAdapter(context!!, dynaList)
        val dynaManager = LinearLayoutManager(context!!)
        listRightClassDyna.layoutManager = dynaManager
        listRightClassDyna.adapter = dynaAdapter

        textTime.onChronometerTickListener = this
    }

    override fun onResume() {
        super.onResume()
        view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        textTime.stop()
        textTime.start()
        val homeActivity = activity as HomeActivity
        val schoolBean: Lesson.DataBean.EquipmentBean = homeActivity.schoolBean
        val name = schoolBean.classX
        val classId = schoolBean.classid
        if (!TextUtils.isEmpty(name)) {
            textRightClassName.text = name
        }
        if (!TextUtils.isEmpty(classId)) {
           this.classId = classId
        }
        contentPresenter?.getclassContent(context!!, this.classId)
        contentPresenter?.getClassDynamics(context!!, this.classId)
    }


    override fun onChronometerTick(chronometer: Chronometer?) {
        contentPresenter?.getClassDynamics(context!!, classId)
    }


    /*****
     * 班风
     */
    override fun getAtmosphereSuccess(atmosphereList: List<String>) {
        this.atmosphereList.clear()
        this.atmosphereList.addAll(atmosphereList)
        atmoAdapter?.notifyDataSetChanged()
    }

    /****
     * 班训
     */

    override fun getMottoSuccess(mottoList: List<String>) {
        this.mottoList.clear()
        this.mottoList.addAll(mottoList)
        mottoAdapter?.notifyDataSetChanged()
    }

    /*****
     * 班主任寄语
     */
    override fun getSlogansSuccess(slogansList: List<String>) {
        this.slogansList.clear()
        this.slogansList.addAll(slogansList)
        slogAdapter?.notifyDataSetChanged()
    }

    /****
     * 班级的内容
     */
    override fun getClassContentSuccess(classContent: String) {
        textRightContent.text = classContent
    }

    /****
     *班级动态
     */
    override fun getClassDynamicsSuccess(dynaList: List<Dynamics>) {
        this.dynaList.clear()
        this.dynaList.addAll(dynaList)
        dynaAdapter?.notifyDataSetChanged()
    }

    override fun showErrorMessage(message: String?) {
        ToastUtil.getInstance().showToast(context, message)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (contentPresenter != null) {
            contentPresenter?.detachView()
            contentPresenter = null
        }
    }

    override fun getSchoolSuccess(content: String, url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}