package com.dsm.dsmmarketandroid.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityLoginBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.MainActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword.ForgotPasswordActivity
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tb_login.setNavigationOnClickListener { finish() }

        tv_forget_password.setOnClickListener { startActivity<ForgotPasswordActivity>() }

        et_password.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.login()
                true
            } else false
        }

        viewModel.intentMainActivityEvent.observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        })

        viewModel.toastLoginFailEvent.observe(this, Observer { toast(getString(R.string.fail_login)) })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.showLoadingDialogEvent.observe(this, Observer { LoadingDialog.show(supportFragmentManager) })

        viewModel.hideLoadingDialogEvent.observe(this, Observer { LoadingDialog.hide() })

        viewModel.hideKeyboardEvent.observe(this, Observer {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(et_password.windowToken, 0)
        })

        binding.viewModel = viewModel
    }
}
