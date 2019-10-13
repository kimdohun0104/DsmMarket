package com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PostImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.PermissionUtil
import com.esafirm.imagepicker.features.ImagePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post_purchase.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostPurchaseActivity : BaseActivity<ActivityPostPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_post_purchase

    companion object {
        private const val SELECT_IMAGE = 1
        private const val CATEGORY = 3
    }

    private val viewModel: PostPurchaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PermissionUtil.requestReadExternalStorage(this)
        binding.isImageSelectVisible = true

        rv_post_image.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = PostImageListAdapter(viewModel)
        rv_post_image.adapter = adapter

        tb_post_purchase.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivityForResult(Intent(this, PostCategoryActivity::class.java), CATEGORY) }

        iv_select_image.setOnClickListener {
            if (PermissionUtil.isReadExternalStorageAllow(this)) {
                PermissionUtil.requestReadExternalStorage(this)
                Snackbar.make(iv_select_image, getString(R.string.fail_permission_denied), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            ImagePicker.create(this@PostPurchaseActivity).limit(5).start(SELECT_IMAGE)
        }

        viewModel.imageList.observe(this, Observer {
            if (it.size == 0) {
                binding.isImageSelectVisible = true
            } else {
                adapter.setItems(it)
                binding.isImageSelectVisible = false
            }
        })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.showLoadingDialogEvent.observe(this, Observer { LoadingDialog.show(supportFragmentManager) })

        viewModel.hideLoadingDialogEvent.observe(this, Observer { LoadingDialog.hide() })

        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                val pathList = arrayListOf<String>()
                ImagePicker.getImages(data).forEach {
                    pathList.add(it.path)
                }
                viewModel.imageList.value = pathList
            } else if (requestCode == CATEGORY) {
                viewModel.category.value = data?.getStringExtra("category")
            }
        }
    }
}
