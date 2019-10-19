package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import android.view.View
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSearchPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.purchaseList.PurchaseListFragment

class SearchPurchaseFragment : BaseFragment<FragmentSearchPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search_purchase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val purchaseListFragment = PurchaseListFragment().apply {
            arguments = Bundle().apply { putString("search", activity?.intent?.getStringExtra("search")) }
        }
        childFragmentManager.beginTransaction().replace(R.id.fl_purchase_container, purchaseListFragment).commit()
    }
}