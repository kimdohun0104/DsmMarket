package com.dsm.dsmmarketandroid.presentation.ui.myPost

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityMyPostBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPostPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_my_post.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPostActivity : BaseActivity<ActivityMyPostBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_my_post

    val viewModel: MyPostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_my_post.setNavigationOnClickListener { finish() }
        var isRentLoaded = false

        vp_my_post.adapter = MyPostPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tl_my_post, vp_my_post, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.purchase)
                1 -> tab.text = getString(R.string.rent)
            }
        }.attach()

        tl_my_post.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_my_post.currentItem = tab!!.position
                if (tab.position == 1) {
                    if (!isRentLoaded) {
                        viewModel.getMyRent()
                        isRentLoaded = true
                    }
                }
            }

        })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        binding.viewModel
    }
}
