package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityCategoryListBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.CategoryPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : BaseActivity<ActivityCategoryListBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_category_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = intent.getStringExtra("category")
        setSupportActionBar(tb_category_list)
        supportActionBar?.title = category
        tb_category_list.setNavigationOnClickListener { finish() }

        vp_category_list.adapter = CategoryPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tl_category_list, vp_category_list, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.purchase)
                1 -> tab.text = getString(R.string.rent)
            }
        }.attach()
    }
}
