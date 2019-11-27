package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentImage

import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRentImageBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_rent_image.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentImageActivity : BaseActivity<ActivityRentImageBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_rent_image

    private val viewModel: RentImageViewModel by viewModel()

    private val postId: Int by lazy { intent.getIntExtra("post_id", -1) }

    override fun viewInit() {
        tb_rent_image.setNavigationOnClickListener { finish() }

        viewModel.getRentImage(postId)
    }

    override fun observeViewModel() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
