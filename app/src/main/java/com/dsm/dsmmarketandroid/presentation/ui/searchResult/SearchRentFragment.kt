package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSearchRentBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SearchRentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity
import kotlinx.android.synthetic.main.fragment_search_rent.*
import org.jetbrains.anko.support.v4.startActivity

class SearchRentFragment : BaseFragment<FragmentSearchRentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search_rent

    private val viewModel: SearchResultViewModel by lazy { ViewModelProviders.of(activity!!)[SearchResultViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search = activity?.intent?.getStringExtra("search")!!

        val adapter = SearchRentListAdapter(viewModel)
        rv_rent.adapter = adapter

        viewModel.rentInit(search)

        viewModel.rentNetworkState.observe(this, Observer { adapter.setNetworkState(it) })

        viewModel.rentListItems.observe(this, Observer { adapter.submitList(it) })

        viewModel.intentRentDetail.observe(this, Observer { startActivity<RentDetailActivity>("post_id" to it) })

        binding.viewModel = viewModel
    }

}