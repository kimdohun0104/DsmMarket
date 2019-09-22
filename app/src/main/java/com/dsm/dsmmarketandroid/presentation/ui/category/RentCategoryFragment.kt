package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.data.paging.NetworkState
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentCategoryBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_rent_category.*

class RentCategoryFragment : BaseFragment<FragmentRentCategoryBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rent_category

    private val viewModel: CategoryListViewModel by lazy { ViewModelProviders.of(activity!!)[CategoryListViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RentListAdapter(activity!!)
        rv_category_rent.adapter = adapter

        viewModel.rentNetworkState.observe(this, Observer {
            if (it == NetworkState.LOADED) pb_loading.visibility = View.GONE
            adapter.setNetworkState(it)
        })

        viewModel.rentList.observe(this, Observer { adapter.submitList(it) })
    }

}