package com.dsm.dsmmarketandroid.presentation.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySplashBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.MainActivity
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.login()

        viewModel.intentMainActivityEvent.observe(this, Observer { startActivity<MainActivity>() })

        viewModel.intentLoginActivityEvent.observe(this, Observer { startActivity<LoginActivity>() })
    }
}
