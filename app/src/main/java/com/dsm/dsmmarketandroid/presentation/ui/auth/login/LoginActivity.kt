package com.dsm.dsmmarketandroid.presentation.ui.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityLoginBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.MainActivity
import com.dsm.dsmmarketandroid.presentation.ui.me.password.forgotPassword.ForgotPasswordActivity
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login

    private val viewModel: LoginViewModel by viewModel()

    override fun viewInit() {
        tb_login.setNavigationOnClickListener { finish() }

        tv_login_forget_password.setOnClickListener { startActivity<ForgotPasswordActivity>() }

        et_login_password.setEditorActionListener(EditorInfo.IME_ACTION_DONE) { if (btn_login.isClickable) viewModel.login() }
    }

    override fun observeViewModel() {
        val `this` = this@LoginActivity
        viewModel.run {
            showLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.show(supportFragmentManager) })

            hideLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.hide() })

            intentMainActivityEvent.observe(`this`, Observer {
                Intent(`this`, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            })

            hideKeyboardEvent.observe(`this`, Observer {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(et_login_password.windowToken, 0)
            })

            toastEvent.observe(`this`, Observer { toast(it) })

            loginLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.LOGIN, it) })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
