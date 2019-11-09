package com.dsm.dsmmarketandroid.presentation.ui.signUp

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.custom.LinePagerIndicatorDecoration
import com.dsm.dsmmarketandroid.databinding.ActivitySignUpBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivityRefac
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SignUpPagerAdapter
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivityRefac<ActivitySignUpBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up

    private val viewModel: SignUpViewModel by viewModel()

    override fun viewInit() {
        tb_sign_up.setNavigationOnClickListener { finish() }

        vp_sign_up.adapter = SignUpPagerAdapter(supportFragmentManager, lifecycle)
        vp_sign_up.addItemDecoration(LinePagerIndicatorDecoration())
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
