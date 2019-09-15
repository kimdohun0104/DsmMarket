package com.dsm.dsmmarketandroid.presentation.ui.recent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRecentPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recent_purchase.*

class RecentPurchaseFragment : BaseFragment<FragmentRecentPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recent_purchase

    private val viewModel: RecentViewModel by lazy { ViewModelProviders.of(activity!!).get(RecentViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecentListAdapter(0, viewModel)
        rv_recent_purchase.adapter = adapter

        viewModel.purchaseList.observe(this, Observer { adapter.setItems(it) })

        binding.viewModel = viewModel
    }

}