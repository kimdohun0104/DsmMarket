package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentPurchaseCategoryBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.purchaseList.PurchaseListFragment

class PurchaseCategoryFragment : BaseFragment<FragmentPurchaseCategoryBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_purchase_category

    override fun viewInit() {
        val purchaseListFragment = PurchaseListFragment()
        purchaseListFragment.arguments = Bundle().apply { putString("category", activity?.intent?.getStringExtra("category")) }
        childFragmentManager.beginTransaction().replace(R.id.fl_purchase_container, purchaseListFragment).commit()
    }

    override fun observeViewModel() {
    }
}