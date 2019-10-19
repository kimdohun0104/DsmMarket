package com.dsm.dsmmarketandroid.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

// TODO Base Activity, Fragment를 상위 패키지의 base로 빼도 좋을 듯
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    abstract val layoutResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }
}