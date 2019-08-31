package com.dsm.dsmmarketandroid.presentation.ui.changeNick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityChangeNickBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_change_nick.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeNickActivity : BaseActivity<ActivityChangeNickBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_change_nick

    private val viewModel: ChangeNickViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_change_name.setNavigationOnClickListener { finish() }
        binding.nick = intent.getStringExtra("nick")

        viewModel.changeNickSuccessEvent.observe(this, Observer { finish() })

        viewModel.existentNickEvent.observe(this, Observer { toast(getString(R.string.fail_existent_nick)) })

        viewModel.serverErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        binding.viewModel = viewModel
    }
}
