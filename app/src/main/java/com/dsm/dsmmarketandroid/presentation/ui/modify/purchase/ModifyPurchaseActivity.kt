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
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import com.dsm.dsmmarketandroid.presentation.util.PermissionUtil
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postId = intent.getIntExtra("post_id", -1)
        binding.postId = postId
        PermissionUtil.requestReadExternalStorage(this)
        tb_modify_purchase.setNavigationOnClickListener { finish() }

        val adapter = ModifyImageListAdapter()
        rv_modify_image.layoutManager = LinearLayoutManager(this@ModifyPurchaseActivity, LinearLayoutManager.HORIZONTAL, false)
        rv_modify_image.adapter = adapter

        cl_category.setOnClickListener { startActivityForResult(Intent(this, PostCategoryActivity::class.java), CATEGORY) }

        viewModel.getPurchaseDetail(postId)

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CATEGORY) {
                viewModel.setCategory(data?.getStringExtra("category") ?: "")
            }
        }
    }
}
