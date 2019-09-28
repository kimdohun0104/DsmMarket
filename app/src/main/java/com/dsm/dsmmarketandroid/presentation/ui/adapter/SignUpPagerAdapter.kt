package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUp1Fragment
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUp2Fragment

@SuppressLint("WrongConstant")
class SignUpPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> SignUp1Fragment()
            1 -> SignUp2Fragment()
            else -> SignUp1Fragment()
        }

    override fun getCount(): Int = 2
}