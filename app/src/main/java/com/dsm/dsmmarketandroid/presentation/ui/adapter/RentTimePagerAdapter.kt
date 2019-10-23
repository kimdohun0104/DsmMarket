package com.dsm.dsmmarketandroid.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime.EndTimeFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime.StartTimeFragment


class RentTimePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> StartTimeFragment()
            else -> EndTimeFragment()
        }
}