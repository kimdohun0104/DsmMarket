package com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentSelectRentTimeBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentTimePagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_select_rent_time.*
import kotlinx.android.synthetic.main.fragment_select_rent_time.view.*

class SelectRentTimeFragment : BottomSheetDialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_select_rent_time, container, false)

        rootView.tl_rent_time.addTab(rootView.tl_rent_time.newTab().setText(R.string.start))
        rootView.tl_rent_time.addTab(rootView.tl_rent_time.newTab().setText(R.string.end))
        rootView.vp_rent_time.adapter = RentTimePagerAdapter(childFragmentManager)
        rootView.vp_rent_time.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(rootView.tl_rent_time))
        rootView.tl_rent_time.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                rootView.vp_rent_time.currentItem = tab!!.position
            }

        })

        rootView.btn_confirm.setOnClickListener { dismiss() }

        return rootView
    }

}