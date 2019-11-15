package com.dsm.dsmmarketandroid.presentation.ui.signUp

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSignUp1Binding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.fragment_sign_up1.*

class SignUp1Fragment : BaseFragment<FragmentSignUp1Binding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up1

    override fun viewInit() {
        et_sign_up_password_confirm.setEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
            activity?.findViewById<Button>(R.id.btn_sign_up)?.let {
                if (it.isClickable) { activity?.findViewById<ViewPager2>(R.id.vp_sign_up)?.setCurrentItem(1, true) }
            }
        }
    }

    override fun observeViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = ViewModelProviders.of(activity!!).get(SignUpViewModel::class.java)
    }
}