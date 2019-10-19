package com.dsm.dsmmarketandroid.presentation.ui.modify.rent.rentTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ModifyRentTimePagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_select_rent_time.view.*

// TODO Post와 Modify 시간 선택 관련 통일하기
class ModifyRentTimeFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_select_rent_time, container, false)

        rootView.tl_rent_time.addTab(rootView.tl_rent_time.newTab().setText(getString(R.string.start)))
        rootView.tl_rent_time.addTab(rootView.tl_rent_time.newTab().setText(getString(R.string.end)))
        rootView.vp_rent_time.adapter = ModifyRentTimePagerAdapter(childFragmentManager)
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