package com.dsm.dsmmarketandroid.presentation.ui.modify.purchase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityModifyPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ModifyImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.post.postCategory.PostCategoryActivity
import kotlinx.android.synthetic.main.activity_modify_purchase.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyPurchaseActivity : BaseActivity<ActivityModifyPurchaseBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_modify_purchase

    companion object {
        private const val CATEGORY = 1
    }

    private val viewModel: ModifyPurchaseViewModel by viewModel()

    override fun viewInit() {
        val postId = intent.getIntExtra("post_id", -1)
        binding.postId = postId

        tb_modify_purchase.setNavigationOnClickListener { finish() }

        (rv_modify_image.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        rv_modify_image.adapter = ModifyImageListAdapter()

        cl_category.setOnClickListener { startActivityForResult(Intent(this, PostCategoryActivity::class.java), CATEGORY) }

        viewModel.getPurchaseDetail(postId)
    }

    override fun observeViewModel() {
        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastEvent.observe(this, Observer { toast(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CATEGORY)
            viewModel.setCategory(data?.getStringExtra("category") ?: "")
    }
}
