package com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityForgotPasswordBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_forgot_password

    private val viewModel: ForgotPasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_forgot_password.setNavigationOnClickListener { finish() }

        et_email.setEditorActionListener(EditorInfo.IME_ACTION_DONE) { if (btn_find_password.isClickable) viewModel.sendTempPassword() }

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.showLoadingDialogEvent.observe(this, Observer { LoadingDialog.show(supportFragmentManager) })

        viewModel.hideLoadingDialogEvent.observe(this, Observer { LoadingDialog.hide() })

        binding.viewModel = viewModel
    }
}
