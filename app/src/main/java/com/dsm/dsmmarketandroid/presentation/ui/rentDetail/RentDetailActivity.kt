package com.dsm.dsmmarketandroid.presentation.ui.rentDetail

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRentDetailBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentActivity
import kotlinx.android.synthetic.main.activity_rent_detail.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentDetailActivity : BaseActivity<ActivityRentDetailBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_rent_detail

    private val viewModel: RentDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postId = intent.getIntExtra("post_id", 0)

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to 1) }

        viewModel.getRentDetail(postId)

        binding.viewModel = viewModel
    }
}
