package com.dsm.dsmmarketandroid.presentation.ui.interest

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentInterestRentBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.InterestRentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_interest_rent.*

class InterestRentFragment : BaseFragment<FragmentInterestRentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_interest_rent

    private val viewModel: InterestViewModel by lazy { ViewModelProviders.of(activity!!)[InterestViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = InterestRentListAdapter(activity!!)
        rv_interest_rent.adapter = adapter

        srl_interest_rent.setOnRefreshListener { viewModel.getInterestRent() }

        viewModel.getInterestRent()

        viewModel.rentList.observe(this, Observer { adapter.setItems(it) })

        viewModel.hideRentProgressEvent.observe(this, Observer { pb_loading.visibility = View.GONE })

        viewModel.hideRentRefresh.observe(this, Observer { srl_interest_rent.isRefreshing = false })

        binding.viewModel = viewModel
    }
}