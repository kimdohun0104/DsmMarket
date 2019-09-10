package com.dsm.dsmmarketandroid.presentation.ui.myPost

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPostPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_my_post.tb_my_post
import kotlinx.android.synthetic.main.activity_my_post.tl_my_post
import kotlinx.android.synthetic.main.activity_my_post.vp_my_post

class MyPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_post)
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
    }
}
