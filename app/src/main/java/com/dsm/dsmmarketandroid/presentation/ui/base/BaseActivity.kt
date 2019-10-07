package com.dsm.dsmmarketandroid.presentation.ui.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dsm.dsmmarketandroid.presentation.BaseApp

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(BaseApp.localeManager?.setLocale(newBase))
    }
}