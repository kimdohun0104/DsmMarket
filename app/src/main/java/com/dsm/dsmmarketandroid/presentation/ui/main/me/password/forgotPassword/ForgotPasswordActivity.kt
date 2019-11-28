package com.dsm.dsmmarketandroid.presentation.ui.main.me.password.forgotPassword

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityForgotPasswordBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_forgot_password

    private val viewModel: ForgotPasswordViewModel by viewModel()

    override fun viewInit() {
        tb_forgot_password.setNavigationOnClickListener { finish() }

        et_forgot_password_email.setEditorActionListener(EditorInfo.IME_ACTION_DONE) {
            if (btn_forgot_password_send_email.isClickable) viewModel.sendTempPassword()
        }
    }

    override fun observeViewModel() {
        val `this` = this@ForgotPasswordActivity
        viewModel.run {
            showLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.show(supportFragmentManager) })

            hideLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.hide() })

            finishActivityEvent.observe(`this`, Observer { finish() })

            toastEvent.observe(`this`, Observer { toast(it) })

            sendTempPasswordLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.SEND_TEMP_PASSWORD, it) })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
