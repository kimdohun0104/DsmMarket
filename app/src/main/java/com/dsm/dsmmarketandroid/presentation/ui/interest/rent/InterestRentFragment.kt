package com.dsm.dsmmarketandroid.presentation.ui.interest.rent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentInterestRentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.InterestListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestViewModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.fragment_interest_rent.*

class InterestRentFragment : BaseFragment<FragmentInterestRentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_interest_rent

    private val viewModel: InterestViewModel by lazy { ViewModelProviders.of(activity!!)[InterestViewModel::class.java] }

    private val adapter = InterestListAdapter(ProductType.RENT)

    override fun viewInit() {
        rv_interest_rent.adapter = adapter

        srl_interest_rent.setOnRefreshListener { viewModel.getInterest(ProductType.RENT) }
    }

    override fun observeViewModel() {
        viewModel.rentList.observe(this, Observer { adapter.setItems(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}