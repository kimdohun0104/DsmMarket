package com.dsm.dsmmarketandroid.presentation.ui.mailConfirm

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityMailConfirmBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.changePassword.ChangePasswordActivity
import kotlinx.android.synthetic.main.activity_mail_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MailConfirmActivity : BaseActivity<ActivityMailConfirmBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_mail_confirm

    private val viewModel: MailConfirmViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_confirm_code.setNavigationOnClickListener { finish() }
        val email = intent.getStringExtra("email")
        viewModel.email.value = email

        viewModel.confirmCodeSuccessEvent.observe(this, Observer { startActivity<ChangePasswordActivity>("email" to email) })

        viewModel.confirmCodeFailEvent.observe(this, Observer { toast(getString(R.string.fail_confirm_code)) })

        viewModel.invalidConfirmCodeEvent.observe(this, Observer { toast(getString(R.string.fail_invalid_confirm_code)) })

        binding.viewModel = viewModel
    }
}
