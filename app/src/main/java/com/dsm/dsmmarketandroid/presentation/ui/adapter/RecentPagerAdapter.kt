package com.dsm.dsmmarketandroid.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentPurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentRentFragment

class RecentPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> RecentPurchaseFragment()
            else -> RecentRentFragment()
        }
}
