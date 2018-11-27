package com.school.nfcard

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import android.widget.Toast
import com.school.nfcard.ui.LoginActivity
import com.school.nfcard.utils.TimeUtils
import kotlinx.android.synthetic.main.dialog_password.*

/**
 *此类的作用：主页面
 *
 * Created by Liu on 2018/10/23.
 *
 */
class PasswordActivity : AppCompatActivity(),
        OnClickListener,
        View.OnTouchListener {


    private val FlagHomeKeyDispatched = -0x80000000
    private var view: View? = null

    companion object {
        fun jumpTo(context: Context) {
            val intent = Intent(context, PasswordActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FlagHomeKeyDispatched, FlagHomeKeyDispatched)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        view = layoutInflater.inflate(R.layout.dialog_password, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = Color.WHITE
        }
        setContentView(view)
        dialog_button0.setOnClickListener(this)
        dialog_button1.setOnClickListener(this)
        dialog_button2.setOnClickListener(this)
        dialog_button3.setOnClickListener(this)
        dialog_button4.setOnClickListener(this)
        dialog_button5.setOnClickListener(this)
        dialog_button6.setOnClickListener(this)
        dialog_button7.setOnClickListener(this)
        dialog_button8.setOnClickListener(this)
        dialog_button9.setOnClickListener(this)
        dialog_buttonD.setOnClickListener(this)
        dialog_button_cancel.setOnClickListener(this)
        dialog_button_sure.setOnClickListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        return true
    }

    override fun onResume() {
        super.onResume()
        view?.setOnTouchListener(this)
        view?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
    }


    var text = ""

    override fun onClick(v: View?) {
        text = textPassword.text.toString().trim()
        when (v?.id) {
            R.id.dialog_button0 -> {
                textPassword.text = text + dialog_button0.text.toString().trim()
            }
            R.id.dialog_button1 -> {
                textPassword.text = text + dialog_button1.text.toString().trim()
            }
            R.id.dialog_button2 -> {
                textPassword.text = text + dialog_button2.text.toString().trim()
            }
            R.id.dialog_button3 -> {
                textPassword.text = text + dialog_button3.text.toString().trim()
            }
            R.id.dialog_button4 -> {
                textPassword.text = text + dialog_button4.text.toString().trim()
            }
            R.id.dialog_button5 -> {
                textPassword.text = text + dialog_button5.text.toString().trim()
            }
            R.id.dialog_button6 -> {
                textPassword.text = text + dialog_button6.text.toString().trim()
            }
            R.id.dialog_button7 -> {
                textPassword.text = text + dialog_button7.text.toString().trim()
            }
            R.id.dialog_button8 -> {
                textPassword.text = text + dialog_button8.text.toString().trim()
            }
            R.id.dialog_button9 -> {
                textPassword.text = text + dialog_button9.text.toString().trim()
            }
            R.id.dialog_buttonD -> {
                textPassword.text = ""
            }
            R.id.dialog_button_cancel -> {
                this.finish()
            }
            R.id.dialog_button_sure -> {
                if (!TextUtils.isEmpty(text)) {
                    if (TextUtils.equals(text, TimeUtils.getCurrentTimeMMDD())) {
                        LoginActivity.jumpTo(this)
                    } else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "请输密码", Toast.LENGTH_SHORT).show()
                }
                this.finish()
            }
        }
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