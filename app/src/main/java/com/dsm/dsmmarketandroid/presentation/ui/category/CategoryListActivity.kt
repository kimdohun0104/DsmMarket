package com.dsm.dsmmarketandroid.presentation.ui.category

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityCategoryListBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.CategoryPagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_category_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryListActivity : BaseActivity<ActivityCategoryListBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_category_list

    private val viewModel: CategoryListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = intent.getStringExtra("category")
        setSupportActionBar(tb_category_list)
        supportActionBar?.title = category
        tb_category_list.setNavigationOnClickListener { finish() }

        tl_category_list.addTab(tl_category_list.newTab().setText(getString(R.string.purchase)))
        tl_category_list.addTab(tl_category_list.newTab().setText(getString(R.string.rent)))
        vp_category_list.adapter = CategoryPagerAdapter(supportFragmentManager)
        vp_category_list.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_category_list))
        tl_category_list.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_category_list.currentItem = tab!!.position
            }

        })

        binding.viewModel = viewModel
    }
}
