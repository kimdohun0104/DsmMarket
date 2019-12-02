package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentImage

import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRentImageBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_rent_image.*

class RentImageActivity : BaseActivity<ActivityRentImageBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_rent_image

    private val img: String by lazy { intent.getStringExtra("img") ?: "" }

    override fun viewInit() {
        tb_rent_image.setNavigationOnClickListener { finish() }
        binding.img = img
    }

    override fun observeViewModel() {
    }
}
