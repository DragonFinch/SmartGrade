package com.school.nfcard

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import com.school.nfcard.entity.Lesson
import com.school.nfcard.presenter.AppUpdatePresenter
import com.school.nfcard.presenter.DownFilePresenter
import com.school.nfcard.presenter.impl.AppUpdateContract
import com.school.nfcard.presenter.impl.DownFileContract
import com.school.nfcard.ui.AppVersionDialog
import com.school.nfcard.ui.LodingDialog
import com.school.nfcard.ui.LoginActivity
import com.school.nfcard.ui.card.PasswordDialog
import com.school.nfcard.ui.fragment.LeftFragment
import com.school.nfcard.ui.fragment.MiddleFragment
import com.school.nfcard.ui.fragment.RightFragment
import com.school.nfcard.utils.FragmentUtils
import com.school.nfcard.utils.PhoneUtils
import com.school.nfcard.utils.TimeUtils
import com.school.nfcard.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_home.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *此类的作用：主页面
 *
 * Created by Liu on 2018/10/23.
 *
 */
class HomeActivity : AppCompatActivity(),
        OnClickListener,
        View.OnTouchListener,
        View.OnLongClickListener,
        AppUpdateContract.View,
        DownFileContract.View,
        AppVersionDialog.DialogItemClickListener {


    private val FlagHomeKeyDispatched = -0x80000000
    private var view: View? = null
    private var millisInFuture: Long = 10000L
    private val countDownInterval: Long = 1000L
    private var testFlag = false   //  false 测试模式  true   生产模式
    var timer: CountDownTimer? = null


    private var appUpdatePresenter: AppUpdatePresenter? = null
    private var downFilePresenter: DownFilePresenter? = null
    private var appVersionDialog: AppVersionDialog? = null
    private var passwordDialog: PasswordDialog? = null
    private var lodingDialog: LodingDialog? = null
    private var downPath: String = ""

    private var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)


    var schoolBean: Lesson.DataBean.EquipmentBean = Lesson.DataBean.EquipmentBean()

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                3000 -> {
                    if (testFlag) {
                        testFlag = false
                        millisInFuture = 1000L * 10
                        ToastUtil.getInstance().showToast(this@HomeActivity, "测试")
                    } else {
                        ToastUtil.getInstance().showToast(this@HomeActivity, "正式")
                        testFlag = true
                        millisInFuture = 1000L * 60 * 60
                    }
                    timer = object : CountDownTimer(millisInFuture, countDownInterval) {
                        override fun onTick(millisUntilFinished: Long) {

                        }

                        override fun onFinish() {
                            timer?.start()
                            appUpdatePresenter?.checkAppVersion(this@HomeActivity)
                        }
                    }
                    timer?.cancel()
                    timer?.start()
                }
                4000 -> {
                    appUpdatePresenter?.checkAppVersion(this@HomeActivity)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FlagHomeKeyDispatched, FlagHomeKeyDispatched)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        view = layoutInflater.inflate(R.layout.activity_home, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = Color.WHITE
        }
        setContentView(view)
        initView()
        initUpdate()

        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                timer?.start()
                appUpdatePresenter?.checkAppVersion(this@HomeActivity)
            }
        }

    }

    /*****
     * 首页信息点击，加载第一个Fragment
     */
    private fun initView() {
        button_left.setOnClickListener(this)
        button_middle.setOnClickListener(this)
        button_right.setOnClickListener(this)
        button_left.setTextColor(Color.parseColor("#ffffff"))
        button_left.setBackgroundResource(R.drawable.shape_rectangle_yellow)
        button_middle.setTextColor(Color.parseColor("#3DA6FF"))
        button_middle.setBackgroundResource(R.drawable.shape_rectangle_white)
        button_right.setTextColor(Color.parseColor("#3DA6FF"))
        button_right.setBackgroundResource(R.drawable.shape_rectangle_white)
        FragmentUtils.replace(this, R.id.layout_home_container, LeftFragment())
        textTitle.setOnClickListener(this)
        textTitle.setOnLongClickListener(this)
        textDate.setOnLongClickListener(this)
    }

    /***
     * 升级功能
     */
    private fun initUpdate() {
        appUpdatePresenter = AppUpdatePresenter(this)
        downFilePresenter = DownFilePresenter(this)
        appVersionDialog = AppVersionDialog(this, R.style.MyDialog)
        lodingDialog = LodingDialog(this, R.style.MyDialog)
        passwordDialog = PasswordDialog(this, R.style.MyDialog)
        appVersionDialog?.setCancelable(false)
        appVersionDialog?.appClickLister = this

    }


    /****
     * onTouch 事件
     * 主要是隐藏底部的导航键，home键，返回键
     */
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        return true
    }

    override fun onResume() {
        super.onResume()
        view?.setOnTouchListener(this)
        textDate.text = TimeUtils.getCurrentTimeYYMMDD()
        timer?.cancel()
        timer?.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_left -> {
                button_left.setTextColor(Color.parseColor("#ffffff"))
                button_left.setBackgroundResource(R.drawable.shape_rectangle_yellow)
                button_middle.setTextColor(Color.parseColor("#3DA6FF"))
                button_middle.setBackgroundResource(R.drawable.shape_rectangle_white)
                button_right.setTextColor(Color.parseColor("#3DA6FF"))
                button_right.setBackgroundResource(R.drawable.shape_rectangle_white)
                FragmentUtils.replace(this, R.id.layout_home_container, LeftFragment())
            }
            R.id.button_middle -> {
                button_left.setTextColor(Color.parseColor("#3DA6FF"))
                button_left.setBackgroundResource(R.drawable.shape_rectangle_white)
                button_middle.setTextColor(Color.parseColor("#ffffff"))
                button_middle.setBackgroundResource(R.drawable.shape_rectangle_yellow)
                button_right.setTextColor(Color.parseColor("#3DA6FF"))
                button_right.setBackgroundResource(R.drawable.shape_rectangle_white)
                FragmentUtils.replace(this, R.id.layout_home_container, MiddleFragment())
            }
            R.id.button_right -> {
                button_left.setTextColor(Color.parseColor("#3DA6FF"))
                button_left.setBackgroundResource(R.drawable.shape_rectangle_white)
                button_middle.setTextColor(Color.parseColor("#3DA6FF"))
                button_middle.setBackgroundResource(R.drawable.shape_rectangle_white)
                button_right.setTextColor(Color.parseColor("#ffffff"))
                button_right.setBackgroundResource(R.drawable.shape_rectangle_yellow)
                FragmentUtils.replace(this, R.id.layout_home_container, RightFragment())
            }

            R.id.textTitle -> {
                PasswordActivity.jumpTo(this)
//                passwordDialog?.show()
//                view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
            }
        }
    }


    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            R.id.textTitle -> {
                handler.sendEmptyMessageDelayed(4000, 3000)
            }
            R.id.textDate -> {
                handler.sendEmptyMessageDelayed(3000, 3000)
            }
        }
        return true
    }

    override fun mustDown(url: String, description: String) {
        this.downPath = url
        downFilePresenter?.downLoadAppByUrl(this, downPath)
    }

    override fun nextDown(url: String, description: String) {
        this.downPath = url
        downFilePresenter?.downLoadAppByUrl(this, downPath)
    }

    override fun showLoading() {
        lodingDialog?.show()
    }

    override fun hideLoading() {
        lodingDialog?.dismiss()
    }

    override fun showErrorMessage(message: String?) {
        ToastUtil.getInstance().showToast(this, message)
    }

    override fun downLoadAppSucsess() {
        //  这个没有用到
    }

    override fun sureDown() {
        appVersionDialog?.dismiss()
        downFilePresenter?.downLoadAppByUrl(this, downPath)
    }

    override fun cancleDown() {
        appVersionDialog?.dismiss()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true
        }
        return false
    }
}