package com.dsm.dsmmarketandroid.presentation.ui.recent

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRecentBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_recent.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecentActivity : BaseActivity<ActivityRecentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_recent

    private val viewModel: RecentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_past.setNavigationOnClickListener { finish() }

        tl_recent.addTab(tl_recent.newTab().setText(getString(R.string.purchase)))
        tl_recent.addTab(tl_recent.newTab().setText(getString(R.string.rent)))
        vp_recent.adapter = RecentPagerAdapter(supportFragmentManager)
        vp_recent.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_recent))
        tl_recent.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_recent.currentItem = tab!!.position
            }

        })

        viewModel.getRecentProduct()

        binding.viewModel = viewModel
    }
}
