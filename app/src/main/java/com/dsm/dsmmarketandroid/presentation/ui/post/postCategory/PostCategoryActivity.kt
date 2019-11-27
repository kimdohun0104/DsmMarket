package com.dsm.dsmmarketandroid.presentation.ui.post.postCategory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostCategoryBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PostCategoryListAdapter
import kotlinx.android.synthetic.main.activity_post_category.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostCategoryActivity : BaseActivity<ActivityPostCategoryBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_post_category

    private val viewModel: PostCategoryViewModel by viewModel()

    private val adapter: PostCategoryListAdapter by lazy { PostCategoryListAdapter(viewModel) }

    override fun viewInit() {
        tb_post_category.setNavigationOnClickListener { finish() }

        rv_post_category.adapter = adapter

        viewModel.getPostCategory()
    }

    override fun observeViewModel() {
        val `this` = this@PostCategoryActivity
        viewModel.run {
            categoryList.observe(`this`, Observer { adapter.addItems(it) })

            toastEvent.observe(`this`, Observer { toast(it) })

            selectedCategory.observe(`this`, Observer {
                Intent().apply {
                    putExtra("category", it)
                    setResult(Activity.RESULT_OK, this)
                    finish()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
