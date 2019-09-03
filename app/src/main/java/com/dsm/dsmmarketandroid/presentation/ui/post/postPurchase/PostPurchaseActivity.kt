package com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PostImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post_purchase.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostPurchaseActivity : BaseActivity<ActivityPostPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_post_purchase

    companion object {
        private const val SELECT_IMAGE = 1
        private const val READ_EXTERNAL_STORAGE_PERMISSION = 2
    }

    private val viewModel: PostPurchaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionRequest()
        binding.isImageSelectVisible = true

        rv_post_image.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = PostImageListAdapter(viewModel)
        rv_post_image.adapter = adapter

        tb_post_purchase.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivity<PostCategoryActivity>() }

        iv_select_image.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                permissionRequest()
                Snackbar.make(iv_select_image, getString(R.string.fail_permission_denied), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent().apply {
                type = "image/*"
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                action = Intent.ACTION_GET_CONTENT
            }
            startActivityForResult(Intent.createChooser(intent, "select image"), SELECT_IMAGE)
        }

        viewModel.imageList.observe(this, Observer {
            if (it.size == 0) {
                binding.isImageSelectVisible = true
            } else {
                adapter.setItems(it)
                binding.isImageSelectVisible = false
            }
        })

        binding.viewModel = viewModel
    }

    private fun permissionRequest() =
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                val clip = data?.clipData
                val uri = arrayListOf<Uri>()
                for (i in 0 until clip?.itemCount!!) {
                    if (i >= 5) {
                        toast(getString(R.string.max_image))
                        break
                    }
                    uri.add(clip.getItemAt(i).uri)
                }

                viewModel.imageList.value = uri
            }
        }
    }
}
