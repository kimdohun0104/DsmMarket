package com.dsm.dsmmarketandroid.presentation.ui.purchaseList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentPurchaseListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ProductListAdapter
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.fragment_purchase_list.*
import kr.sdusb.libs.messagebus.MessageBus
import kr.sdusb.libs.messagebus.Subscribe
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PurchaseListFragment : BaseFragment<FragmentPurchaseListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_purchase_list

    private val search: String by lazy { arguments?.getString("search") ?: "" }
    private val category: String by lazy { arguments?.getString("category") ?: "" }

    private val purchaseDataFactory: PurchaseDataFactory by inject { parametersOf(search, category) }
    private val viewModel: PurchaseListViewModel by viewModel { parametersOf(purchaseDataFactory) }

    private val adapter = ProductListAdapter(ProductType.PURCHASE)

    override fun viewInit() {
        srl_purchase_list.setOnRefreshListener { viewModel.refreshList() }

        rv_purchase_list.adapter = adapter
    }

    override fun observeViewModel() {
        viewModel.networkState.observe(this, Observer {
            if (it == NetworkState.LOADED) {
                pb_loading.visibility = View.GONE
                srl_purchase_list.isRefreshing = false
            }
            adapter.setNetworkState(it)
        })

        viewModel.purchaseItems.observe(this, Observer { adapter.submitList(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MessageBus.getInstance().register(this)
    }

    override fun onDestroy() {
        MessageBus.getInstance().unregister(this)
        super.onDestroy()
    }

    @Subscribe(events = [MessageEvents.SCROLL_TO_TOP_PURCHASE])
    fun scrollToTop() {
        rv_purchase_list.layoutManager!!.smoothScrollToPosition(
            rv_purchase_list,
            RecyclerView.State(),
            0
        )
    }
}