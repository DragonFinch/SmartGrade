package com.school.nfcard.ui.fragment

import android.annotation.SuppressLint
import android.os.*
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import com.school.nfcard.HomeActivity
import com.school.nfcard.R
import com.school.nfcard.camera.CameraInterface
import com.school.nfcard.camera.DisplayUtil
import com.school.nfcard.camera.FileUtil
import com.school.nfcard.entity.Lesson
import com.school.nfcard.entity.Student
import com.school.nfcard.entity.SwipeCard
import com.school.nfcard.icard.ComBean
import com.school.nfcard.icard.MyFunc
import com.school.nfcard.icard.SerialHelper
import com.school.nfcard.presenter.GetContactPresenter
import com.school.nfcard.presenter.UploadFilePresenter
import com.school.nfcard.presenter.impl.GetContactContract
import com.school.nfcard.presenter.impl.UploadFileContrat
import com.school.nfcard.ui.TipDialog
import com.school.nfcard.ui.card.CardEvent
import com.school.nfcard.ui.card.CardPagerAdapter
import com.school.nfcard.ui.card.ShadowTransformer
import com.school.nfcard.utils.DateUtils
import com.school.nfcard.utils.PhoneUtils
import com.school.nfcard.utils.ToastUtil
import com.xinzhidi.nfc.widget.timetable.TimetableAdapter
import kotlinx.android.synthetic.main.fragment_left.*
import java.text.SimpleDateFormat
import java.util.*


/**
 *此类的作用：智能班牌
 *
 * Created by Liu on 2018/10/23.
 *
 */
class LeftFragment : Fragment(),
        GetContactContract.View,
        TextToSpeech.OnInitListener,
        Chronometer.OnChronometerTickListener,
        CameraInterface.CamOpenOverCallback,
        UploadFileContrat.View,
        FileUtil.OnTakePictureSuccessLister,
        View.OnClickListener {


    private val dayOfWeek = 8
    private var presenter: GetContactPresenter? = null
    private var adapter: TimetableAdapter? = null
    private var mSpeech: TextToSpeech? = null
    private var uploadFilePresenter: UploadFilePresenter? = null
    private var timeFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
    private var comA: SerialControl? = null
    private var displayQueue: DisplayQueueThread? = null
    private var msgTip: String? = ""
    private var sMsg: String? = ""
    private var studentName: String = ""
    private val list = ArrayList<String>()
    private val tempList = ArrayList<String>()
    private var head = ""
    private var previewRate = -1f
    private var tipDialog: TipDialog? = null
    private var time = ""


    private lateinit var cardAdapte: CardPagerAdapter
    private lateinit var transformer: ShadowTransformer
    private val TIME = 4000
    private var itemPosition: Int = 0
    private var classId = ""


    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1000 -> CameraInterface.getInstance().doOpenCamera(this@LeftFragment)
                2000 -> {
                    tipDialog?.dismisDialog()
                    view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
                    FileUtil.deleteFile(head)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_left, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FileUtil.setTakePictureLister(this)
        adapter = TimetableAdapter(list)
        val manager = GridLayoutManager(context, dayOfWeek)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        presenter = GetContactPresenter(this)
        uploadFilePresenter = UploadFilePresenter(this)
        textTime.onChronometerTickListener = this
        comA = SerialControl()
        openComPort(comA!!)
        displayQueue = DisplayQueueThread()
        displayQueue?.start()
        tipDialog = TipDialog(context!!, R.style.MyDialog)
        cardAdapte = CardPagerAdapter()
      //  textTime.setOnClickListener(this)
    }


    /***
     * 测试使用
     */
    override fun onClick(v: View?) {
        dispRecData()
    }

    /***
     * 测试使用
     */
    private fun dispRecData() {
        try {
            sMsg = "E00401503C728923"
            if (TextUtils.isEmpty(sMsg)) {
                msgTip = "刷卡失败，重新刷卡"
                initTTS()
            } else {
                CameraInterface.getInstance().doTakePicture()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onResume() {
        super.onResume()
        try {
            textTime.start()
            view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
            presenter!!.getContact(context!!, PhoneUtils.getUniqueId(context!!), "1")
            presenter!!.getLesson(context!!, PhoneUtils.getUniqueId(context!!))
            handler.sendEmptyMessageDelayed(1000, 1000)
            this.previewRate = DisplayUtil.getScreenRate(context!!)
            handler.postDelayed(runnableForViewPager, TIME.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onPause() {
        super.onPause()
        CameraInterface.getInstance().doStopCamera()
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

    /****
     * 拍照成功，开始上传图片
     */
    override fun getPicturePath(path: String?) {
        head = path!!
        uploadFilePresenter?.uploadFileOSS(context, path)
    }

    /****
     * 图片上传成功，开始调用刷卡的接口
     */
    override fun uploadSucess(path: String) {
        presenter?.swipCard(context!!, sMsg!!, path)
    }


    /****
     * 照相机打开了
     */
    override fun cameraHasOpened() {
        val holder = cameraView.surfaceHolder
        CameraInterface.getInstance().doStartPreview(holder, previewRate)
    }

    /****
     * 时钟控件的显示
     */
    override fun onChronometerTick(chronometer: Chronometer?) {
        try {
            val tp: Long = System.currentTimeMillis()
            textTime.text = timeFormat.format(Date(tp))
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
            time = timeFormat.format(Date(tp))
            if (time.endsWith("00")) {
                presenter!!.getLesson(context!!, PhoneUtils.getUniqueId(context!!))
            }

            if (time.endsWith("00:00")) {
                presenter!!.getClassEvent(context!!, classId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /****
     * TTS 语音的初始化
     */
    override fun onInit(status: Int) {
        if (TextToSpeech.SUCCESS === status) {
            val result = mSpeech?.setLanguage(Locale.ENGLISH)
            if (result != TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED) {
                mSpeech?.speak(msgTip, TextToSpeech.QUEUE_ADD, null)
            }
        }
    }


    override fun showErrorMessage(message: String?) {
        ToastUtil.getInstance().showToast(context, message)
    }


    /****
     * 获取课程表的数据成功
     */
    override fun getLessones(schoolName: String, className: String, classId: String, lessonList: List<String>) {
        try {
            this.classId = classId
            presenter!!.getClassEvent(context!!, classId)
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
        val homeActivity = activity as HomeActivity
        homeActivity.schoolBean = schoolBean
    }


    override fun bindingCard(erro: String) {
        ToastUtil.getInstance().showToast(context, erro)
    }

    override fun bindingDevice(erro: String) {
        msgTip = erro + "请先绑定班牌设备"
    }


    /****
     * 数据库中插入刷卡的数据成功
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
                        activity?.runOnUiThread { dispRecData(comData) }
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

    /****
     * 开始解析卡号
     */
    private fun dispRecData(ComRecData: ComBean) {
        try {
            //    16进制卡号
            sMsg = MyFunc.byteArrToHex(ComRecData.bRec)
            //  十进制的卡号
            // sMsg = String(ComRecData.bRec).trim()
            openComPort(comA!!)
            if (TextUtils.isEmpty(sMsg)) {
                msgTip = "刷卡失败，重新刷卡"
                initTTS()
            } else {
                CameraInterface.getInstance().doTakePicture()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /****
     * 倒计时功能的成功
     */

    override fun getDynaSuccess(cardList: List<CardEvent>) {
        if (cardList.isEmpty()) {
            viewPager.visibility = View.GONE
        } else {
            for (event in cardList) {
                cardAdapte.addCardItem(event)
            }
            transformer = ShadowTransformer(viewPager, cardAdapte)
            viewPager.adapter = cardAdapte
            viewPager.setPageTransformer(false, transformer)
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
            ToastUtil.getInstance().showToast(context, "打开串口失败")
        }
    }

    /****
     * 关闭串口
     */
    private fun closeComPort(comPort: SerialHelper) {
        comPort.stopSend()
        comPort.close()
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
        mSpeech = TextToSpeech(context, this)
    }

    internal var runnableForViewPager: Runnable = object : Runnable {
        override fun run() {
            try {
                itemPosition++
                handler.postDelayed(this, TIME.toLong())
                viewPager.currentItem = itemPosition % cardAdapte.count
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    /****
     * 这个方法暂时用不到了
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
     * 这个方法暂时用不到了
     */
    override fun sendToParentSuccess() {
        msgTip = "刷卡成功,$studentName"
        if (!TextUtils.isEmpty(studentName)) {
        }
        initTTS()
    }


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}