package com.dsm.dsmmarketandroid.presentation.ui.main.category

import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityCategoryListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.CategoryPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : BaseActivity<ActivityCategoryListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_category_list

    override fun viewInit() {
        setSupportActionBar(tb_category_list)
        supportActionBar?.title = intent.getStringExtra("category")
        tb_category_list.setNavigationOnClickListener { finish() }

        vp_category_list.adapter = CategoryPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tl_category_list, vp_category_list, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.purchase)
                1 -> tab.text = getString(R.string.rent)
            }
        }.attach()
    }

    override fun observeViewModel() {
    }
}

