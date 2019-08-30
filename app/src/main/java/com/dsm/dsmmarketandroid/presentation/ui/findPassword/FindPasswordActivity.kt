package com.dsm.dsmmarketandroid.presentation.ui.findPassword

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityFindPasswordBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.changePassword.ChangePasswordActivity
import com.dsm.dsmmarketandroid.presentation.ui.mailConfirm.MailConfirmActivity
import kotlinx.android.synthetic.main.activity_find_password.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindPasswordActivity : BaseActivity<ActivityFindPasswordBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_find_password

    private val viewModel: FindPasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tb_login.setNavigationOnClickListener { finish() }

        btn_find_password.setOnClickListener { startActivity<ChangePasswordActivity>() }

        viewModel.sendEmailFailEvent.observe(this, Observer { toast(getString(R.string.fail_send_email)) })

        viewModel.sendEmailSuccessEvent.observe(this, Observer { startActivity<MailConfirmActivity>("email" to et_email.text.toString()) })

        binding.viewModel = viewModel
    }
}
