package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.interest.purchase.InterestPurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.interest.rent.InterestRentFragment

@SuppressLint("WrongConstant")
class InterestPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> InterestPurchaseFragment()
            1 -> InterestRentFragment()
            else -> InterestPurchaseFragment()
        }

    override fun getCount(): Int = 2
}