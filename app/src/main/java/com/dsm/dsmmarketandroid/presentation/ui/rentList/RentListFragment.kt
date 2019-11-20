package com.dsm.dsmmarketandroid.presentation.ui.rentList

import android.view.View
import androidx.lifecycle.Observer
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ProductListAdapter
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.fragment_rent_list.*
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

    private val adapter = ProductListAdapter(ProductType.RENT)

    override fun viewInit() {
        srl_rent_list.setOnRefreshListener { viewModel.refreshList() }

        rv_rent_list.adapter = adapter
    }

    override fun observeViewModel() {
        val `this` = this@RentListFragment
        viewModel.run {
            networkState.observe(`this`, Observer {
                if (it == NetworkState.LOADED) {
                    pb_loading.visibility = View.GONE
                    srl_rent_list.isRefreshing = false
                }
                adapter.setNetworkState(it)
            })

            rentItems.observe(`this`, Observer { adapter.submitList(it) })
        }
        
    }
}