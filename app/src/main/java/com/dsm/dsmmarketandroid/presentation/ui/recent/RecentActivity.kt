package com.dsm.dsmmarketandroid.presentation.ui.recent

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRecentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivityRefac
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecentPagerAdapter
import com.dsm.dsmmarketandroid.presentation.util.addOnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_recent.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecentActivity : BaseActivityRefac<ActivityRecentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_recent

    private val viewModel: RecentViewModel by viewModel()

    override fun viewInit() {
        tb_past.setNavigationOnClickListener { finish() }

        vp_recent.adapter = RecentPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tl_recent, vp_recent, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.purchase)
                1 -> tab.text = getString(R.string.rent)
            }
        }.attach()

        tl_recent.addOnTabSelectedListener { vp_recent.currentItem = it.position }

        viewModel.getRecentProduct()
    }

    override fun observeViewModel() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
