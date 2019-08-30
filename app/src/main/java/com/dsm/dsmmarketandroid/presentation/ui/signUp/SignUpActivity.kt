package com.dsm.dsmmarketandroid.presentation.ui.signUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySignUpBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SignUpPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tb_sign_up.setNavigationOnClickListener { finish() }

        vp_sign_up.adapter = SignUpPagerAdapter(supportFragmentManager)
        ci_sign_up.setupWithViewPager(vp_sign_up)

        viewModel.emailInvalidEvent.observe(this, Observer { toast(getString(R.string.fail_invalid_email)) })

        viewModel.passwordDiffEvent.observe(this, Observer { toast(getString(R.string.fail_diff_password)) })

        viewModel.signUpSuccessEvent.observe(this, Observer { finish() })

        viewModel.signUpFailEvent.observe(this, Observer { toast(getString(R.string.fail_sign_up)) })

        viewModel.existentEmailEvent.observe(this, Observer { toast(getString(R.string.fail_existent_email)) })

        viewModel.existentNameEvent.observe(this, Observer { toast(getString(R.string.fail_existent_nick)) })

        binding.viewModel = viewModel
    }
}
