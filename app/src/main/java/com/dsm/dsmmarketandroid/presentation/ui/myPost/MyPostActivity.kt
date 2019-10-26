package com.dsm.dsmmarketandroid.presentation.ui.myPost

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityMyPostBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPostPagerAdapter
import com.dsm.dsmmarketandroid.presentation.util.addOnTabSelectedListener
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

        tl_my_post.addOnTabSelectedListener {
            vp_my_post.currentItem = it.position
            if (it.position == 1) {
                if (!isRentLoaded) {
                    viewModel.getMyRent()
                    isRentLoaded = true
                }
            }
        }

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        binding.viewModel
    }
}
