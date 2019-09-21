package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.category.PurchaseCategoryFragment
import com.dsm.dsmmarketandroid.presentation.ui.category.RentCategoryFragment

@SuppressLint("WrongConstant")
class CategoryPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> PurchaseCategoryFragment()
            else -> RentCategoryFragment()
        }

    override fun getCount(): Int = 2
}