package com.dsm.dsmmarketandroid.presentation.ui.modify.rent.rentTime

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentModifyEndTimeBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.ModifyRentViewModel
import kotlinx.android.synthetic.main.fragment_end_time.*

class ModifyEndTimeFragment : BaseFragment<FragmentModifyEndTimeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_modify_end_time

    private val viewModel: ModifyRentViewModel by lazy { ViewModelProviders.of(activity!!)[ModifyRentViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        np_hour.minValue = 0
        np_hour.maxValue = 23

        np_minute.minValue = 0
        np_minute.maxValue = 1
        np_minute.displayedValues = arrayOf("00", "30")

        binding.viewModel = viewModel
    }

}