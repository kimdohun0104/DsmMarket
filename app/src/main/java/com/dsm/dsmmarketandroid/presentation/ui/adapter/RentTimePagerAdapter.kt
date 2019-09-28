package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime.EndTimeFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime.StartTimeFragment


@SuppressLint("WrongConstant")
class RentTimePagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> StartTimeFragment()
            1 -> EndTimeFragment()
            else -> StartTimeFragment()
        }

    override fun getCount(): Int = 2
}