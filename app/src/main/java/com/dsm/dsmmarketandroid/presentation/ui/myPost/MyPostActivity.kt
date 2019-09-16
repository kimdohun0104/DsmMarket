package com.dsm.dsmmarketandroid.presentation.ui.myPost

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityMyPostBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPostPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.modify.ModifyPurchaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.modify.ModifyRentActivity
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailActivity
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_my_post.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPostActivity : BaseActivity<ActivityMyPostBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_my_post

    val viewModel: MyPostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_my_post.setNavigationOnClickListener { finish() }

        tl_my_post.addTab(tl_my_post.newTab().setText(R.string.purchase))
        tl_my_post.addTab(tl_my_post.newTab().setText(R.string.rent))
        vp_my_post.adapter = MyPostPagerAdapter(supportFragmentManager)
        vp_my_post.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_my_post))
        tl_my_post.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_my_post.currentItem = tab!!.position
            }

        })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.intentPurchaseDetail.observe(this, Observer { startActivity<PurchaseDetailActivity>("post_id" to it) })

        viewModel.intentPurchaseModify.observe(this, Observer { startActivity<ModifyPurchaseActivity>("post_id" to it) })

        viewModel.intentRentDetail.observe(this, Observer { startActivity<RentDetailActivity>("post_id" to it) })

        viewModel.intentRentModify.observe(this, Observer { startActivity<ModifyRentActivity>("post_id" to it) })

        binding.viewModel
    }
}
