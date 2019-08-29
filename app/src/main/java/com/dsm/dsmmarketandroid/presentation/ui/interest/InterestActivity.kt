package com.dsm.dsmmarketandroid.presentation.ui.interest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.InterestPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_interest.*

class InterestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)
        tb_interest.setNavigationOnClickListener { finish() }

        tl_interest.addTab(tl_interest.newTab().setText(R.string.purchase))
        tl_interest.addTab(tl_interest.newTab().setText(R.string.rent))
        vp_interest.adapter = InterestPagerAdapter(supportFragmentManager)
        vp_interest.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_interest))
        tl_interest.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_interest.currentItem = tab!!.position
            }

        })
    }
}
