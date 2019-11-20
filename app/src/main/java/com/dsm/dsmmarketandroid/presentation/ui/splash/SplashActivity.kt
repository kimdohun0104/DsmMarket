package com.dsm.dsmmarketandroid.presentation.ui.splash

import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySplashBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.MainActivity
import com.dsm.dsmmarketandroid.presentation.ui.start.StartActivity
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun viewInit() {
        viewModel.login()
    }

    override fun observeViewModel() {
        val `this` = this@SplashActivity
        viewModel.run {
            intentStartActivity.observe(`this`, Observer { startActivity<StartActivity>() })

            intentMainActivityEvent.observe(`this`, Observer { startActivity<MainActivity>() })

            finishActivityEvent.observe(`this`, Observer { finish() })
        }
    }
}
