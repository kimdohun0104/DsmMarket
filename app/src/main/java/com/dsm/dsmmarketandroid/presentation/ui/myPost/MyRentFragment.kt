package com.dsm.dsmmarketandroid.presentation.ui.myPost

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMyRentBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyRentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_rent.*

class MyRentFragment : BaseFragment<FragmentMyRentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_rent

    private val viewModel: MyPostViewModel by lazy { ViewModelProviders.of(activity!!)[MyPostViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MyRentListAdapter(activity!!, childFragmentManager)
        rv_my_post_rent.adapter = adapter

        srl_my_rent.setOnRefreshListener { viewModel.getMyRent() }

        viewModel.rentList.observe(this, Observer { adapter.setItems(it) })

        viewModel.deletePositionFromRent.observe(this, Observer { adapter.deleteAt(it) })

        viewModel.hideRentLoadingEvent.observe(this, Observer { pb_loading.visibility = View.GONE })

        viewModel.hideRentRefresh.observe(this, Observer { srl_my_rent.isRefreshing = false })

        binding.viewModel = viewModel
    }
}