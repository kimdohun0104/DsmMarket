package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSearchPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.purchaseList.PurchaseListFragment

class SearchPurchaseFragment : BaseFragment<FragmentSearchPurchaseBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_search_purchase

    override fun viewInit() {
        val bundle = Bundle().apply { putString("search", activity?.intent?.getStringExtra("search")) }
        val purchaseListFragment = PurchaseListFragment().apply { arguments = bundle }
        childFragmentManager.beginTransaction().replace(R.id.fl_purchase_container, purchaseListFragment).commit()
    }

    override fun observeViewModel() {
    }
}