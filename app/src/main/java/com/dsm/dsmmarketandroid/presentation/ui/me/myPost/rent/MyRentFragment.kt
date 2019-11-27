package com.dsm.dsmmarketandroid.presentation.ui.me.myPost.rent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMyRentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyProductListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.me.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.fragment_my_rent.*

class MyRentFragment : BaseFragment<FragmentMyRentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_my_rent

    private val viewModel: MyPostViewModel by lazy { ViewModelProviders.of(activity!!)[MyPostViewModel::class.java] }

    val adapter: MyProductListAdapter by lazy { MyProductListAdapter(ProductType.RENT, childFragmentManager) }

    override fun viewInit() {
        rv_my_post_rent.adapter = adapter

        srl_my_rent.setOnRefreshListener { viewModel.getMyPost(ProductType.RENT) }
    }

    override fun observeViewModel() {
        viewModel.rentList.observe(this, Observer { adapter.setItems(it) })

        viewModel.deletePositionFromRent.observe(this, Observer { adapter.deleteAt(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}