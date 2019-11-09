package com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityForgotPasswordBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivityRefac
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : BaseActivityRefac<ActivityForgotPasswordBinding>() {

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
        viewModel.showLoadingDialogEvent.observe(this, Observer { LoadingDialog.show(supportFragmentManager) })

        viewModel.hideLoadingDialogEvent.observe(this, Observer { LoadingDialog.hide() })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastEvent.observe(this, Observer { toast(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
