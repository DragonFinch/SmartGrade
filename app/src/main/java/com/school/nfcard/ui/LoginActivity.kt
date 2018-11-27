package com.school.nfcard.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.provider.Settings
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.school.nfcard.R
import com.school.nfcard.entity.SchoolInfo
import com.school.nfcard.presenter.LoginPresenter
import com.school.nfcard.presenter.impl.LoginContract
import kotlinx.android.synthetic.main.activity_login.*


/**
 *此类的作用：XXXXXX
 *
 * Created by Liu on 2018/9/13.
 *
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    var presenter: LoginPresenter? = null
    private val FlagHomeKeyDispatched = -0x80000000

    companion object {
        fun jumpTo(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FlagHomeKeyDispatched, FlagHomeKeyDispatched)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        btnActivityLogin.setOnClickListener(this)
        layoutLoginParent.setOnClickListener(this)
        btnActivitySetting.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnActivityLogin -> {
                btnActivityLogin.isClickable = false
                presenter?.login(this, editLoginCode.text.toString().trim(), editLoginPassword.text.toString().trim())
            }
            R.id.layoutLoginParent -> {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (imm.isActive && currentFocus != null) {
                    if (currentFocus.windowToken != null) {
                        imm.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                }
            }
            R.id.btnActivitySetting -> {
                startActivity(Intent(Settings.ACTION_SETTINGS))
                this.finish()
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
        btnActivityLogin.isClickable = true
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun loginSuccess() {
        btnActivityLogin.isClickable = true
        BindingActivity.jumpTo(this)
        this.finish()
    }


    //========================以下方法用不到=============================================
    override fun bindingSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}