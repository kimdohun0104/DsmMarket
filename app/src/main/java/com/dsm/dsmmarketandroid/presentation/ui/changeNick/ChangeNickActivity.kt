package com.dsm.dsmmarketandroid.presentation.ui.changeNick

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityChangeNickBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_change_nick.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeNickActivity : BaseActivity<ActivityChangeNickBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_change_nick

    private val viewModel: ChangeNickViewModel by viewModel()

    override fun viewInit() {
        binding.nick = intent.getStringExtra("nick")

        tb_change_name.setNavigationOnClickListener { finish() }

        et_nick.setEditorActionListener(EditorInfo.IME_ACTION_DONE) { if (btn_change_nick.isClickable) viewModel.changeNick() }
    }

    override fun observeViewModel() {
        val `this` = this@ChangeNickActivity
        viewModel.run {
            finishActivityEvent.observe(`this`, Observer { finish() })

            toastEvent.observe(`this`, Observer { toast(it) })

            changeNickLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.CHANGE_NICK, it) })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}