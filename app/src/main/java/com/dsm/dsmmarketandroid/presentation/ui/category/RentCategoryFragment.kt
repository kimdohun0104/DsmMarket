package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentCategoryBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentList.RentListFragment

class RentCategoryFragment : BaseFragment<FragmentRentCategoryBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_rent_category

    override fun viewInit() {
        val rentListFragment = RentListFragment()
        rentListFragment.arguments = Bundle().apply { putString("category", activity?.intent?.getStringExtra("category")) }
        childFragmentManager.beginTransaction().replace(R.id.fl_rent_container, rentListFragment).commit()
    }

    override fun observeViewModel() {
    }
}