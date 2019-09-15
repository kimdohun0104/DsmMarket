package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentPurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentRentFragment

@SuppressLint("WrongConstant")
class RecentPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> RecentPurchaseFragment()
            else -> RecentRentFragment()
        }

    override fun getCount(): Int = 2

}
