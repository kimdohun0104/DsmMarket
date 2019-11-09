package com.dsm.dsmmarketandroid.presentation.ui.splash

import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySplashBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivityRefac
import com.dsm.dsmmarketandroid.presentation.ui.main.MainActivity
import com.dsm.dsmmarketandroid.presentation.ui.start.StartActivity
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivityRefac<ActivitySplashBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun viewInit() {
        viewModel.login()
    }

    override fun observeViewModel() {
        viewModel.intentStartActivity.observe(this, Observer { startActivity<StartActivity>() })

        viewModel.intentMainActivityEvent.observe(this, Observer { startActivity<MainActivity>() })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })
    }
}
