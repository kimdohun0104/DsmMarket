package com.dsm.dsmmarketandroid.presentation.ui.postCategory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostCategoryBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PostCategoryListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_post_category.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostCategoryActivity : BaseActivity<ActivityPostCategoryBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_post_category

    private val viewModel: PostCategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_post_category.setNavigationOnClickListener { finish() }

        viewModel.getPostCategory()

        val adapter = PostCategoryListAdapter(viewModel)
        rv_post_category.adapter = adapter

        viewModel.categoryList.observe(this, Observer { adapter.addItems(it) })

        viewModel.serverErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.selectedCategory.observe(this, Observer {
            Intent().apply {
                putExtra("category", it)
                setResult(Activity.RESULT_OK, this)
                finish()
            }
        })

        binding.viewModel = viewModel
    }
}
