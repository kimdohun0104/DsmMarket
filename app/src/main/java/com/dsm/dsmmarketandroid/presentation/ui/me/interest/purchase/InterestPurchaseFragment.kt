package com.dsm.dsmmarketandroid.presentation.ui.me.interest.purchase

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentInterestPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.InterestListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.me.interest.InterestViewModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.fragment_interest_purchase.*

class InterestPurchaseFragment : BaseFragment<FragmentInterestPurchaseBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_interest_purchase

    private val viewModel: InterestViewModel by lazy { ViewModelProviders.of(activity!!)[InterestViewModel::class.java] }

    private val adapter = InterestListAdapter(ProductType.PURCHASE)

    override fun viewInit() {
        rv_interest_purchase.adapter = adapter

        srl_interest_purchase.setOnRefreshListener { viewModel.getInterest(ProductType.PURCHASE) }

        viewModel.getInterest(ProductType.PURCHASE)
    }

    override fun observeViewModel() {
        viewModel.purchaseList.observe(this, Observer { adapter.setItems(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}