package com.dsm.dsmmarketandroid.presentation.ui.main.post.postRent.rentTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentTimePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.dialog_select_rent_time.*

class SelectRentTimeDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_select_rent_time, container, false)

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