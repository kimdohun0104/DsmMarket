package com.dsm.dsmmarketandroid.presentation.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.changeName.ChangeNameActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordActivity
import com.dsm.dsmmarketandroid.presentation.ui.past.PastActivity
import kotlinx.android.synthetic.main.fragment_me.view.*
import org.jetbrains.anko.startActivity

class MeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_me, container, false)

        rootView.cl_interest.setOnClickListener { activity?.startActivity<InterestActivity>() }

        rootView.cl_post_product.setOnClickListener { activity?.startActivity<MyPostActivity>() }

        rootView.cl_past_product.setOnClickListener { activity?.startActivity<PastActivity>() }

        rootView.cl_change_name.setOnClickListener { activity?.startActivity<ChangeNameActivity>() }

        rootView.cl_change_password.setOnClickListener { activity?.startActivity<ChangePasswordActivity>() }

        return rootView
    }
}