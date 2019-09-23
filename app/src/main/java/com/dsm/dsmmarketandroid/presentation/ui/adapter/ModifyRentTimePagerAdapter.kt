package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.rentTime.ModifyEndTimeFragment
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.rentTime.ModifyStartTimeFragment

@SuppressLint("WrongConstant")
class ModifyRentTimePagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> ModifyStartTimeFragment()
            else -> ModifyEndTimeFragment()
        }

    override fun getCount(): Int = 2

}