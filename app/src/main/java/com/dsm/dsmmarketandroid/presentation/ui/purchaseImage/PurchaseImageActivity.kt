package com.dsm.dsmmarketandroid.presentation.ui.purchaseImage

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.custom.LinePagerIndicatorDecoration
import com.dsm.dsmmarketandroid.databinding.ActivityPurchaseImageBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PurchaseImageDetailListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_purchase_image.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PurchaseImageActivity : BaseActivity<ActivityPurchaseImageBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_purchase_image

    private val viewModel: PurchaseImageViewModel by viewModel()

    private val postId: Int by lazy { intent.getIntExtra("post_id", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_purchase_image.setNavigationOnClickListener { finish() }

        val adapter = PurchaseImageDetailListAdapter()
        rv_purchase_image.adapter = adapter
        (rv_purchase_image.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        PagerSnapHelper().attachToRecyclerView(rv_purchase_image)
        rv_purchase_image.addItemDecoration(LinePagerIndicatorDecoration())

        viewModel.getPurchaseImage(postId)

        viewModel.imageList.observe(this, Observer { adapter.listItems = it })

        binding.viewModel = viewModel
    }
}
