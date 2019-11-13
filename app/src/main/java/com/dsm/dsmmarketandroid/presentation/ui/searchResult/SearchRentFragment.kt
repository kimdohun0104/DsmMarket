package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSearchRentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rentList.RentListFragment

class SearchRentFragment : BaseFragment<FragmentSearchRentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_search_rent

    override fun viewInit() {
        val bundle = Bundle().apply { putString("search", activity?.intent?.getStringExtra("search")) }
        val rentListFragment = RentListFragment().apply { arguments = bundle }
        childFragmentManager.beginTransaction().replace(R.id.fl_rent_container, rentListFragment).commit()
    }

    override fun observeViewModel() {
    }
}