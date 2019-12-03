package com.dsm.dsmmarketandroid.presentation.ui.main.me.myPost.purchase

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMyPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyProductListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.main.me.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.fragment_my_purchase.*

class MyPurchaseFragment : BaseFragment<FragmentMyPurchaseBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_my_purchase

    private val viewModel: MyPostViewModel by lazy { ViewModelProviders.of(activity!!)[MyPostViewModel::class.java] }

    private val adapter: MyProductListAdapter by lazy { MyProductListAdapter(ProductType.PURCHASE, childFragmentManager) }

    override fun viewInit() {
        rv_my_post_purchase.adapter = adapter

        srl_my_purchase.setOnRefreshListener { viewModel.getMyPost(ProductType.PURCHASE) }

        viewModel.getMyPost(ProductType.PURCHASE)
    }

    override fun observeViewModel() {
        viewModel.purchaseList.observe(this, Observer { adapter.setItems(it) })

        viewModel.deletePositionFromPurchase.observe(this, Observer { adapter.deleteAt(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}
