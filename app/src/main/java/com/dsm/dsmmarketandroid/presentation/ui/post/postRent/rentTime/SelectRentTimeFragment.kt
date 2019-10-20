package com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentTimePagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_select_rent_time.*

class SelectRentTimeFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_select_rent_time, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vp_rent_time.adapter = RentTimePagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tl_rent_time, vp_rent_time, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.start)
                1 -> tab.text = getString(R.string.end)
            }
        }.attach()

        btn_confirm.setOnClickListener { dismiss() }
    }
}