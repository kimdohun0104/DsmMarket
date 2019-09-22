package com.dsm.dsmmarketandroid.presentation.ui.purchase

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PurchaseListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.category.CategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_purchase.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PurchaseFragment : BaseFragment<FragmentPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_purchase

    private val purchaseDataFactory: PurchaseDataFactory by inject { parametersOf("", "") }
    private val viewModel: PurchaseViewModel by viewModel { parametersOf(purchaseDataFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.setTitle(R.string.purchase)

        val adapter = PurchaseListAdapter(activity!!)
        rv_purchase.adapter = adapter

        viewModel.purchaseListItems.observe(this, Observer { adapter.submitList(it) })

        viewModel.networkState.observe(this, Observer {
            if (it == NetworkState.LOADED) pb_loading.visibility = View.GONE
            adapter.setNetworkState(it)
        })

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_main_toolbar, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> activity?.startActivity<SearchActivity>()
            R.id.category -> activity?.startActivity<CategoryActivity>()
            R.id.interest -> activity?.startActivity<InterestActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}