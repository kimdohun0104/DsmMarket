package com.dsm.dsmmarketandroid.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dsm.dsmmarketandroid.presentation.BaseApp

abstract class BaseActivityRefac<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    abstract val layoutResourceId: Int

    abstract fun viewInit()

    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

        viewInit()
        observeViewModel()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(BaseApp.localeManager?.setLocale(newBase))
    }
}