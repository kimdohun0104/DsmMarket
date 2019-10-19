package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import android.view.View
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSearchRentBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rentList.RentListFragment

class SearchRentFragment : BaseFragment<FragmentSearchRentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search_rent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rentListFragment = RentListFragment()
        rentListFragment.arguments = Bundle().apply { putString("search", activity?.intent?.getStringExtra("search")) }
        childFragmentManager.beginTransaction().replace(R.id.fl_rent_container, rentListFragment).commit()
    }
}