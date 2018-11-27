package com.school.nfcard

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Chronometer
import com.school.nfcard.camera.CameraInterface
import com.school.nfcard.camera.DisplayUtil
import com.school.nfcard.camera.FileUtil
import com.school.nfcard.camera.FileUtil.setTakePictureLister
import com.school.nfcard.entity.ParentMsg
import com.school.nfcard.entity.Student
import com.school.nfcard.entity.SwipeCard
import com.school.nfcard.icard.ComBean
import com.school.nfcard.icard.SerialHelper
import com.school.nfcard.presenter.AppUpdatePresenter
import com.school.nfcard.presenter.DownFilePresenter
import com.school.nfcard.presenter.GetContactPresenter
import com.school.nfcard.presenter.UploadFilePresenter
import com.school.nfcard.presenter.impl.AppUpdateContract
import com.school.nfcard.presenter.impl.DownFileContract
import com.school.nfcard.presenter.impl.GetContactContract
import com.school.nfcard.presenter.impl.UploadFileContrat
import com.school.nfcard.ui.AppVersionDialog
import com.school.nfcard.ui.LodingDialog
import com.school.nfcard.ui.LoginActivity
import com.xinzhidi.nfc.widget.timetable.TimetableAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.os.CountDownTimer
import android.widget.Toast
import com.school.nfcard.entity.Lesson
import com.school.nfcard.ui.TipDialog
import com.school.nfcard.ui.card.CardEvent
import com.school.nfcard.utils.*

class MainActivity : AppCompatActivity(),
        GetContactContract.View,
        TextToSpeech.OnInitListener,
        Chronometer.OnChronometerTickListener,
        CameraInterface.CamOpenOverCallback,
        UploadFileContrat.View,
        View.OnClickListener,
        FileUtil.OnTakePictureSuccessLister,
        AppUpdateContract.View,
        DownFileContract.View,
        AppVersionDialog.DialogItemClickListener,
        View.OnLongClickListener {
    override fun getDynaSuccess(cardList: List<CardEvent>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val dayOfWeek = 8
    private var presenter: GetContactPresenter? = null
    private var adapter: TimetableAdapter? = null
    private var mSpeech: TextToSpeech? = null


    private var uploadFilePresenter: UploadFilePresenter? = null
    private var appUpdatePresenter: AppUpdatePresenter? = null
    private var downFilePresenter: DownFilePresenter? = null

    private var appVersionDialog: AppVersionDialog? = null
    private var lodingDialog: LodingDialog? = null
    private var tipDialog: TipDialog? = null

    private var timeFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)

    private var comA: SerialControl? = null
    private var displayQueue: DisplayQueueThread? = null

    private var msgTip: String? = ""
    private var sMsg: String? = ""

    private var studentName: String = ""
    private var logo: String = "http://i1.umei.cc/uploads/tu/201808/9999/b7f19b205e.jpg"
    private val FLAG_HOMEKEY_DISPATCHED = -0x80000000

    private var downPath: String = ""

    private val list = ArrayList<String>()
    private val tempList = ArrayList<String>()


    private var millisInFuture: Long = 10000L
    private val countDownInterval: Long = 1000L

    private var testFlag = false   //  false 测试模式        true   生产模式

    var timer: CountDownTimer? = null

    private var head = ""

    private var previewRate = -1f
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1000 -> CameraInterface.getInstance().doOpenCamera(this@MainActivity)
                2000 -> tipDialog?.dismisDialog()
                3000 -> {
                    if (testFlag) {
                        testFlag = false
                        millisInFuture = 1000L * 10
                        ToastUtil.getInstance().showToast(this@MainActivity, "测试")
                    } else {
                        ToastUtil.getInstance().showToast(this@MainActivity, "正式")
                        testFlag = true
                        millisInFuture = 1000L * 60 * 60
                    }
                    timer = object : CountDownTimer(millisInFuture, countDownInterval) {
                        override fun onTick(millisUntilFinished: Long) {

                        }

                        override fun onFinish() {
                            timer?.start()
                            presenter!!.getLesson(this@MainActivity, PhoneUtils.getUniqueId(this@MainActivity))
                            appUpdatePresenter?.checkAppVersion(this@MainActivity)
                        }
                    }
                    timer?.cancel()
                    timer?.start()
                }

                4000 -> {
                    appUpdatePresenter?.checkAppVersion(this@MainActivity)
                }
            }
        }
    }


    private var view: View? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        view = layoutInflater.inflate(R.layout.activity_main, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = Color.WHITE
        }
        setContentView(view)
        textTitle.setOnClickListener(this)
        textTitle.setOnLongClickListener(this)
        textDate.setOnLongClickListener(this)
        setTakePictureLister(this)
        adapter = TimetableAdapter(list)
        val manager = GridLayoutManager(this, dayOfWeek)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        presenter = GetContactPresenter(this)
        uploadFilePresenter = UploadFilePresenter(this)
        appUpdatePresenter = AppUpdatePresenter(this)
        downFilePresenter = DownFilePresenter(this)
        textTime.onChronometerTickListener = this
        comA = SerialControl()
        openComPort(comA!!)
        displayQueue = DisplayQueueThread()
        displayQueue?.start()

        appVersionDialog = AppVersionDialog(this, R.style.MyDialog)
        lodingDialog = LodingDialog(this, R.style.MyDialog)
        tipDialog = TipDialog(this, R.style.MyDialog)

        appVersionDialog?.setCancelable(false)
        appVersionDialog?.appClickLister = this

        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                timer?.start()
                presenter!!.getLesson(this@MainActivity, PhoneUtils.getUniqueId(this@MainActivity))
                appUpdatePresenter?.checkAppVersion(this@MainActivity)
            }
        }

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textTitle -> {
                LoginActivity.jumpTo(this)
//                presenter?.swipCard(this, "3507903040", "http://s16.sinaimg.cn/mw690/006wmg2Hzy73dosCjG7cf")
            }
        }
    }

    /*****
     * 长按检测版本升级
     */
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

    /****
     * 语音功能初始化
     */
    private fun initTTS() {
        if (mSpeech != null) {
            mSpeech?.stop()
            mSpeech?.shutdown()
            mSpeech = null
        }
        mSpeech = TextToSpeech(this@MainActivity, this)
    }

    override fun onResume() {
        super.onResume()
        try {
            textTime.start()
            view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
            presenter!!.getContact(this, PhoneUtils.getUniqueId(this), "1")
            presenter!!.getLesson(this, PhoneUtils.getUniqueId(this))
            handler.sendEmptyMessageDelayed(1000, 1000)
            this.previewRate = DisplayUtil.getScreenRate(this)
            timer?.cancel()
            timer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /****
     * 打开串口
     */
    private fun openComPort(comPort: SerialHelper) {
        comA?.port = "/dev/ttyS3"
        comA?.setBaudRate("9600")
        try {
            comPort.open()
        } catch (e: Exception) {
            ToastUtil.getInstance().showToast(this, "打开串口失败")
        }
    }


    var filePath: String? = null

    override fun getPicturePath(path: String?) {
        filePath = path
        uploadFilePresenter?.uploadFileOSS(this, path)
    }


    /*****
     * 图片上传成功
     */
    override fun uploadSucess(path: String) {
        head = path
        FileUtil.deleteFile(filePath)
        Toast.makeText(this, sMsg, Toast.LENGTH_LONG).show()
        presenter?.swipCard(this, sMsg!!, path)
    }


    /****
     * 关闭串口
     */
    private fun closeComPort(comPort: SerialHelper) {
        comPort.stopSend()
        comPort.close()
    }

    /****
     * 显示接收的数据
     */
    private val parentList: MutableList<ParentMsg> = mutableListOf<ParentMsg>()


    private fun dispRecData(ComRecData: ComBean) {
        try {
            //    16进制卡号
            //  sMsg = MyFunc.byteArrToHex(ComRecData.bRec)

            //  十进制的卡号
            sMsg = String(ComRecData.bRec).trim()
//            presenter?.swipCard(this, sMsg!!, logo)
            // String(ComRecData.bRec).trim()
            // ToastUtil.getInstance().showToast(this, sMsg)
            openComPort(comA!!)
            if (TextUtils.isEmpty(sMsg)) {
                msgTip = "刷卡失败，重新刷卡"
                initTTS()
            }
            else {
                CameraInterface.getInstance().doTakePicture()
//                msgTip = "刷卡成功"
//                val list: List<Student> = StudentHelper.getStudent(sMsg)
//                if (list.isNotEmpty()) {
//                    parentList.clear()
//                    for (student in list) {
//                        cardNO = student.cardno
//                        studentName = student.name
//                        studentid = student.id
//                        val msg = ParentMsg(student.parentname, student.device_tokens, student.ios_or_android)
//                        parentList.add(msg)
//                    }
//                    if (parentList.size > 0) {
//                        val json = GsonUtils.getInstance().classToJson(parentList)
//                        val ampm = DateUtils.isBelong("09:00", "17:30")
//                        when (ampm) {
//                            "am" -> {
//                                presenter?.sendToParent(this, "7884CACEFD7", studentid, studentName, "1", json, logo)
//                            }
//                            "pm" -> {
//                                presenter?.sendToParent(this, "7884CACEFD7", studentid, studentName, "2", json, logo)
//                            }
//                            "work" -> {
//                                msgTip = "上课期间，不用刷卡"
//                                initTTS()
//                            }
//                        }
//                    } else {
//                        msgTip = "家长信息错误"
//                        initTTS()
//                    }
//                } else {
//                    msgTip = "还没有绑定卡号"
//                    initTTS()
//                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /*****
     * 上传图像成功，刷卡成功
     */


    override fun swipCardSuccess(swipeCard: SwipeCard.DataBean) {
        if (!TextUtils.isEmpty(swipeCard.name)) {
            tipDialog?.showDialog(swipeCard.name, head)
            handler.sendEmptyMessageDelayed(2000, 1500)
            msgTip = "刷卡成功,${swipeCard.name}"
            initTTS()
        }
        textSumTotal.text = "应到" + swipeCard.total + "人"
        textSumReal.text = "实到" + swipeCard.signstud + "人"
        val un = swipeCard.total - swipeCard.signstud
        textSumNot.text = "未到" + un + "人"
    }


    /****
     * 计时监听事件，随时随地的监听时间的变化
     */
    override fun onChronometerTick(p0: Chronometer?) {
        try {
            val tp: Long = System.currentTimeMillis()
            textTime.text = timeFormat.format(Date(tp))
            textDate.text = dateFormat.format(Date(tp))
            val week = DateUtils.getDayOfWeek()
            for (i in 0 until tempList.size) {
                val time = tempList[i]
                if (time.contains("-")) {
                    val pos = time.indexOf("-")
                    val startTime = time.substring(0, pos)
                    val endTime = time.substring(pos + 1, time.length)
                    val nowTime = DateUtils.getDate()
                    val start = DateUtils.dateToStamp("$nowTime $startTime")
                    val end = DateUtils.dateToStamp("$nowTime $endTime")
                    val now = System.currentTimeMillis()
                    if (now in start..end) {
                        val pos = (i + 1) * 8 + week
                        textCurrentCourse.text = list[pos]
                        break
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun sureDown() {
        appVersionDialog?.dismiss()
        downFilePresenter?.downLoadAppByUrl(this, downPath)
    }

    override fun cancleDown() {
        appVersionDialog?.dismiss()
    }

    override fun downLoadAppSucsess() {

    }

    override fun mustDown(url: String, description: String) {
//        appVersionDialog?.show()
//        appVersionDialog?.hideCancle()
        this.downPath = url
        downFilePresenter?.downLoadAppByUrl(this, downPath)
//        appVersionDialog?.setDialogContent(description)
    }

    override fun nextDown(url: String, description: String) {
//        appVersionDialog?.show()
        this.downPath = url
        downFilePresenter?.downLoadAppByUrl(this, downPath)
//        appVersionDialog?.setDialogContent(description)
    }

    override fun showLoading() {
        lodingDialog?.show()
    }

    override fun hideLoading() {
        lodingDialog?.dismiss()
    }

    /****
     *
     * 语音朗读功能 初始化
     */

    override fun onInit(status: Int) {
        if (TextToSpeech.SUCCESS === status) {
            val result = mSpeech?.setLanguage(Locale.ENGLISH)
            if (result != TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED) {
                mSpeech?.speak(msgTip, TextToSpeech.QUEUE_ADD, null)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        if (mSpeech != null) {
            mSpeech!!.stop()
            mSpeech!!.shutdown()
            mSpeech = null
        }
        closeComPort(comA!!)
    }

    /*****
     * 网络请求显示错误信息
     */
    override fun showErrorMessage(message: String) {
        ToastUtil.getInstance().showToast(this, message)
    }


    override fun bindingCard(erro: String) {
        ToastUtil.getInstance().showToast(this, erro)
    }

    override fun bindingDevice(erro: String) {
        msgTip = erro + "请先绑定班牌设备"
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun sendToParentSuccess() {
        msgTip = "刷卡成功,$studentName"
        if (!TextUtils.isEmpty(studentName)) {
//            CameraInterface.getInstance().doTakePicture()
//            ToastUtils.getInstance().showToast(this, this.studentName!!, logo)
        }
        initTTS()
    }

    /****
     * 摄像头打开了
     */

    override fun cameraHasOpened() {
        val holder = cameraView.surfaceHolder
        CameraInterface.getInstance().doStartPreview(holder, previewRate)
    }

    /*****
     * 请求数据成功
     */
    override fun getContactSuccess(list: List<Student>) {
        try {
            if (TextUtils.isEmpty(sMsg)) {
                msgTip = "刷卡失败，重新刷卡"
            } else {
                for (student in list) {
                    val cardNO = student.cardno
                    if (cardNO == sMsg) {
                        studentName = student.name
                        msgTip = "刷卡成功,$studentName"
                        break
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /****
     * 获取课程表成功
     *
     * 获取 班牌的学校名称 和 班级名称成功
     */

    override fun getLessones(schoolName: String, className: String, classId: String, lessonList: List<String>) {
        try {
            if (TextUtils.isEmpty(className)) {
                return
            }
            if (lessonList.size <= 8) {
                msgTip = "请为该班级添加课程表"
                // ToastUtil.getInstance().showToast(this, msgTip)
            }
            textSchoolName.text = schoolName
            textGrade.text = className
            list.clear()
            list.addAll(lessonList)
            adapter?.notifyDataSetChanged()
            for (i in 8 until list.size) {
                if (i % 8 == 0) {
                    val time = list[i]
                    tempList.add(time)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getSchoolInfo(schoolBean: Lesson.DataBean.EquipmentBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class SerialControl : SerialHelper() {
        override fun onDataReceived(ComRecData: ComBean?) {
            displayQueue?.addQueue(ComRecData!!)
        }
    }

    /*****
     * 接收数据的线程
     */
    inner class DisplayQueueThread : Thread() {
        private val queueList = LinkedList<ComBean>()
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun run() {
            super.run()
            while (!isInterrupted) {
                var comData: ComBean?
                do {
                    comData = queueList.poll()
                    if (comData != null) {
                        runOnUiThread { dispRecData(comData) }
                        try {
                            Thread.sleep(500)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } while (true)
            }
        }

        @Synchronized
        fun addQueue(comData: ComBean) {
            queueList.add(comData)
        }
    }


    override fun onPause() {
        super.onPause()
        CameraInterface.getInstance().doStopCamera()
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
