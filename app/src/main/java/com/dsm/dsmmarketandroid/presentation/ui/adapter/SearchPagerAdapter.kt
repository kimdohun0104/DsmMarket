package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.searchResult.SearchPurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.searchResult.SearchRentFragment

@SuppressLint("WrongConstant")
class SearchPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> SearchPurchaseFragment()
            else -> SearchRentFragment()
        }

    override fun getCount(): Int = 2

}