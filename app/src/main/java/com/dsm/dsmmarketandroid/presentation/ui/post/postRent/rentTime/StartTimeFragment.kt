package com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentStartTimeBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import kotlinx.android.synthetic.main.fragment_end_time.*

class StartTimeFragment : BaseFragment<FragmentStartTimeBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_start_time

    override fun viewInit() {
        np_hour.minValue = 0
        np_hour.maxValue = 23

        np_minute.minValue = 0
        np_minute.maxValue = 1
        np_minute.displayedValues = arrayOf("00", "30")
    }

    override fun observeViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = ViewModelProviders.of(activity!!)[PostRentViewModel::class.java]
    }
}