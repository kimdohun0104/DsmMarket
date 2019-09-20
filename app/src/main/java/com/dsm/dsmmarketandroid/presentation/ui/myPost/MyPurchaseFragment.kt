package com.dsm.dsmmarketandroid.presentation.ui.myPost

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMyPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPurchaseListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_purchase.*

class MyPurchaseFragment : BaseFragment<FragmentMyPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_purchase

    private val viewModel: MyPostViewModel by lazy { ViewModelProviders.of(activity!!)[MyPostViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MyPurchaseListAdapter(viewModel, childFragmentManager)
        rv_my_post_purchase.adapter = adapter

        viewModel.getMyPurchase()

        viewModel.purchaseList.observe(this, Observer { adapter.setItems(it) })

        viewModel.deletePositionFromPurchase.observe(this, Observer { adapter.deleteAt(it) })
    }
}
