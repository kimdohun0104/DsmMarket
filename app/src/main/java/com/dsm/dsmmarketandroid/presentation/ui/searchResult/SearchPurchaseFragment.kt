package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.data.paging.NetworkState
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSearchPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PurchaseListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_purchase.*

class SearchPurchaseFragment : BaseFragment<FragmentSearchPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search_purchase

    private val viewModel: SearchResultViewModel by lazy { ViewModelProviders.of(activity!!)[SearchResultViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PurchaseListAdapter(activity!!)
        rv_purchase.adapter = adapter

        srl_search_purchase.setOnRefreshListener {
            viewModel.refreshPurchase()
            srl_search_purchase.isRefreshing = false
        }

        viewModel.purchaseNetworkState.observe(this, Observer {
            if (it == NetworkState.LOADED) pb_loading.visibility = View.GONE
            adapter.setNetworkState(it)
        })

        viewModel.purchaseListItems.observe(this, Observer { adapter.submitList(it) })

        binding.viewModel = viewModel
    }
}