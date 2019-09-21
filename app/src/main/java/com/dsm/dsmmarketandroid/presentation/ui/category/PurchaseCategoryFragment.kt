package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentPurchaseCategoryBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PurchaseCategoryListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_purchase_category.*

class PurchaseCategoryFragment : BaseFragment<FragmentPurchaseCategoryBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_purchase_category

    private val viewModel: CategoryListViewModel by lazy { ViewModelProviders.of(activity!!)[CategoryListViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = activity?.intent?.getStringExtra("category") ?: ""

        val adapter = PurchaseCategoryListAdapter(viewModel)
        rv_category_purchase.adapter = adapter

        viewModel.purchaseInit(category)

        viewModel.purchaseNetworkState.observe(this, Observer { adapter.setNetworkState(it) })

        viewModel.purchaseList.observe(this, Observer { adapter.submitList(it) })
    }
}