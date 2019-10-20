package com.dsm.dsmmarketandroid.presentation.ui.rentList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity
import kotlinx.android.synthetic.main.fragment_rent_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RentListFragment : BaseFragment<FragmentRentListBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rent_list

    private val search: String by lazy { arguments?.getString("search") ?: "" }
    private val category: String by lazy { arguments?.getString("category") ?: "" }
    private val rentDataFactory: RentDataFactory by inject { parametersOf(search, category) }
    private val viewModel: RentListViewModel by viewModel { parametersOf(rentDataFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        srl_rent_list.setOnRefreshListener {
            viewModel.refreshList()
            srl_rent_list.isRefreshing = false
        }

        val adapter = RentListAdapter(activity!!)
        rv_rent_list.adapter = adapter

        viewModel.rentListItems.observe(this, Observer { adapter.submitList(it) })

        viewModel.networkState.observe(this, Observer {
            if (it == NetworkState.LOADED) pb_loading.visibility = View.GONE
            adapter.setNetworkState(it)
        })

        viewModel.intentRentDetail.observe(this, Observer { startActivity<RentDetailActivity>("post_id" to it) })
    }
}