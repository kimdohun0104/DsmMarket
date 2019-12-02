package com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseImage

import androidx.recyclerview.widget.PagerSnapHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.custom.LinePagerIndicatorDecoration
import com.dsm.dsmmarketandroid.databinding.ActivityPurchaseImageBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PurchaseImageDetailListAdapter
import kotlinx.android.synthetic.main.activity_purchase_image.*

class PurchaseImageActivity : BaseActivity<ActivityPurchaseImageBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_purchase_image

    private val img: List<String> by lazy { intent.getStringArrayListExtra("img") }

    override fun viewInit() {
        tb_purchase_image.setNavigationOnClickListener { finish() }

        val imageAdapter = PurchaseImageDetailListAdapter()
        rv_purchase_image.run {
            adapter = imageAdapter
            rv_purchase_image.addItemDecoration(LinePagerIndicatorDecoration())
            PagerSnapHelper().attachToRecyclerView(this)
        }

        imageAdapter.addItems(img)
    }

    override fun observeViewModel() {
    }
}
