package com.dsm.dsmmarketandroid.presentation.ui.me

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMeBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.changeNick.ChangeNickActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordActivity
import com.dsm.dsmmarketandroid.presentation.ui.past.PastActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MeFragment : BaseFragment<FragmentMeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_me

    private val viewModel: MeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cl_interest.setOnClickListener { activity?.startActivity<InterestActivity>() }
        cl_post_product.setOnClickListener { activity?.startActivity<MyPostActivity>() }
        cl_past_product.setOnClickListener { activity?.startActivity<PastActivity>() }
        cl_change_name.setOnClickListener { activity?.startActivity<ChangeNickActivity>("nick" to activity?.title) }
        cl_change_password.setOnClickListener { activity?.startActivity<ChangePasswordActivity>() }

        viewModel.userNick.observe(this, Observer { activity?.title = it })

        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserNick()
    }
}