package com.dsm.dsmmarketandroid.presentation.ui.interest

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentInterestPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.InterestPurchaseListAdapter
import kotlinx.android.synthetic.main.fragment_interest_purchase.*

class InterestPurchaseFragment : BaseFragment<FragmentInterestPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_interest_purchase

    private val viewModel: InterestViewModel by lazy { ViewModelProviders.of(activity!!)[InterestViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = InterestPurchaseListAdapter(activity!!)
        rv_interest_purchase.adapter = adapter

        srl_interest_purchase.setOnRefreshListener { viewModel.getInterestPurchase() }

        viewModel.getInterestPurchase()

        viewModel.purchaseList.observe(this, Observer { adapter.setItems(it) })

        viewModel.hidePurchaseProgressEvent.observe(this, Observer { pb_loading.visibility = View.GONE })

        viewModel.hidePurchaseRefresh.observe(this, Observer { srl_interest_purchase.isRefreshing = false })

        binding.viewModel = viewModel
    }
}