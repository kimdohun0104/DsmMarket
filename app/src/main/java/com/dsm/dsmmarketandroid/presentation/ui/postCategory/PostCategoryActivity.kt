package com.dsm.dsmmarketandroid.presentation.ui.postCategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostCategoryBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostCategoryActivity : BaseActivity<ActivityPostCategoryBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_post_category

    private val viewModel: PostCategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPostCategory()

        binding.viewModel = viewModel
    }
}
