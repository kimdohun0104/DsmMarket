package com.dsm.dsmmarketandroid.presentation.ui.myPost.purchase

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMyPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPurchaseListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostViewModel
import kotlinx.android.synthetic.main.fragment_my_purchase.*

class MyPurchaseFragment : BaseFragment<FragmentMyPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_purchase

    private val viewModel: MyPostViewModel by lazy { ViewModelProviders.of(activity!!)[MyPostViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MyPurchaseListAdapter(activity!!, childFragmentManager)
        rv_my_post_purchase.adapter = adapter

        srl_my_purchase.setOnRefreshListener { viewModel.getMyPurchase() }

        viewModel.getMyPurchase()

        viewModel.purchaseList.observe(this, Observer { adapter.setItems(it) })

        viewModel.deletePositionFromPurchase.observe(this, Observer { adapter.deleteAt(it) })

        viewModel.hidePurchaseLoadingEvent.observe(this, Observer { pb_loading.visibility = View.GONE })

        viewModel.hidePurchaseRefresh.observe(this, Observer { srl_my_purchase.isRefreshing = false })

        binding.viewModel = viewModel
    }
}
