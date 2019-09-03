package com.dsm.dsmmarketandroid.presentation.ui.postCategory

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
        viewModel.getPostCategory()

        val adapter = PostCategoryListAdapter()
        rv_post_category.adapter = adapter

        viewModel.categoryList.observe(this, Observer { adapter.addItems(it) })

        viewModel.serverErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        binding.viewModel = viewModel
    }
}
