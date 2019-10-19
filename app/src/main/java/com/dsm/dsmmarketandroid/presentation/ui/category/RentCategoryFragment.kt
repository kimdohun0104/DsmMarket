package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import android.view.View
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentCategoryBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rentList.RentListFragment

class RentCategoryFragment : BaseFragment<FragmentRentCategoryBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rent_category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rentListFragment = RentListFragment()
        rentListFragment.arguments = Bundle().apply { putString("category", activity?.intent?.getStringExtra("category")) }
        childFragmentManager.beginTransaction().replace(R.id.fl_rent_container, rentListFragment).commit()
    }
}