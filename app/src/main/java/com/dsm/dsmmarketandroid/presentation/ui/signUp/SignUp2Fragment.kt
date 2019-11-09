package com.dsm.dsmmarketandroid.presentation.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSignUp2Binding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment

class SignUp2Fragment : BaseFragment<FragmentSignUp2Binding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = ViewModelProviders.of(activity!!).get(SignUpViewModel::class.java)
    }
}