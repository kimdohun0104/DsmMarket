package com.dsm.dsmmarketandroid.presentation.ui.password.sendPasswordCode

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySendPasswordCodeBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm.PasswordCodeConfirmActivity
import kotlinx.android.synthetic.main.activity_send_password_code.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendPasswordCodeActivity : BaseActivity<ActivitySendPasswordCodeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_send_password_code

    private val viewModel: SendPasswordCodeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tb_login.setNavigationOnClickListener { finish() }

        et_email.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.sendPasswordCode()
                true
            } else false
        }

        viewModel.toastInvalidEmailEvent.observe(this, Observer { toast(getString(R.string.fail_invalid_email)) })

        viewModel.intentPasswordCodeConfirmWithEmail.observe(this, Observer { startActivity<PasswordCodeConfirmActivity>("email" to it) })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.finishActivityResult.observe(this, Observer { finish() })

        binding.viewModel = viewModel
    }
}
