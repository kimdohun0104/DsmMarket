package com.dsm.dsmmarketandroid.presentation.ui.modify.rent.rentTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ModifyRentTimePagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_select_rent_time.view.*

// TODO Post와 Modify 시간 선택 관련 통일하기
// TODO onViewCreated로 분리
class ModifyRentTimeFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_select_rent_time, container, false)

        rootView.vp_rent_time.adapter = ModifyRentTimePagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(rootView.tl_rent_time, rootView.vp_rent_time, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.start)
                1 -> tab.text = getString(R.string.end)
            }
        }.attach()
        rootView.btn_confirm.setOnClickListener { dismiss() }

        return rootView
    }
}