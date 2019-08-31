package com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPasswordCodeConfirmBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_password_code_confirm.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordCodeConfirmActivity : BaseActivity<ActivityPasswordCodeConfirmBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_password_code_confirm

    private val viewModel: PasswordCodeConfirmViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_confirm_code.setNavigationOnClickListener { finish() }

        binding.viewModel = viewModel
    }
}
