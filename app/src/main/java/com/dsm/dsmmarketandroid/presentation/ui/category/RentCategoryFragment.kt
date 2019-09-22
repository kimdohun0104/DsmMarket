package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentCategoryBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_rent_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentCategoryFragment : BaseFragment<FragmentRentCategoryBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rent_category

    private val viewModel: CategoryListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = activity?.intent?.getStringExtra("category") ?: ""

        val adapter = RentListAdapter(activity!!)
        rv_category_rent.adapter = adapter

        viewModel.rentInit(category)

        viewModel.rentNetworkState.observe(this, Observer { adapter.setNetworkState(it) })

        viewModel.rentList.observe(this, Observer { adapter.submitList(it) })
    }

}