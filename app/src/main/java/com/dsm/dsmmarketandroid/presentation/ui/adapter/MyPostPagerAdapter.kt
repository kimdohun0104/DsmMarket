package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.myPost.purchase.MyPurchasePostFragment
import com.dsm.dsmmarketandroid.presentation.ui.myPost.rent.MyRentPostFragment

@SuppressLint("WrongConstant")
class MyPostPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MyPurchasePostFragment()
            1 -> MyRentPostFragment()
            else -> MyPurchasePostFragment()
        }

    override fun getCount(): Int = 2
}