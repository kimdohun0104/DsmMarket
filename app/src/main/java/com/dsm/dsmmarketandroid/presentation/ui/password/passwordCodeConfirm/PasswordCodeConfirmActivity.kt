package com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPasswordCodeConfirmBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordActivity
import kotlinx.android.synthetic.main.activity_password_code_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordCodeConfirmActivity : BaseActivity<ActivityPasswordCodeConfirmBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_password_code_confirm

    private val viewModel: PasswordCodeConfirmViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_confirm_code.setNavigationOnClickListener { finish() }
        val email = intent.getStringExtra("email")!!
        binding.email = email

        et_code.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.confirmPasswordCode(email)
                true
            } else false
        }

        viewModel.intentChangePassword.observe(this, Observer {
            startActivity<ChangePasswordActivity>("authCode" to it, "email" to email)
        })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastConfirmCodeFailEvent.observe(this, Observer { toast(R.string.fail_confirm_code) })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(R.string.fail_server_error) })

        binding.viewModel = viewModel
    }
}
