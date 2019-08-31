package com.dsm.dsmmarketandroid.presentation.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMeBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.changeName.ChangeNameActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordActivity
import com.dsm.dsmmarketandroid.presentation.ui.past.PastActivity
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_me.view.*
import kotlinx.android.synthetic.main.fragment_me.view.cl_change_name
import kotlinx.android.synthetic.main.fragment_me.view.cl_change_password
import kotlinx.android.synthetic.main.fragment_me.view.cl_interest
import kotlinx.android.synthetic.main.fragment_me.view.cl_past_product
import kotlinx.android.synthetic.main.fragment_me.view.cl_post_product
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MeFragment : BaseFragment<FragmentMeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_me

    private val viewModel: MeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserNick()

        cl_interest.setOnClickListener { activity?.startActivity<InterestActivity>() }
        cl_post_product.setOnClickListener { activity?.startActivity<MyPostActivity>() }
        cl_past_product.setOnClickListener { activity?.startActivity<PastActivity>() }
        cl_change_name.setOnClickListener { activity?.startActivity<ChangeNameActivity>() }
        cl_change_password.setOnClickListener { activity?.startActivity<ChangePasswordActivity>() }

        viewModel.userNick.observe(this, Observer{ activity?.title = it })

        binding.viewModel = viewModel
    }

}