package com.dsm.dsmmarketandroid.presentation.ui.main.post.postPurchase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PostImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postCategory.PostCategoryActivity
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.PermissionUtil
import com.dsm.mediapicker.MediaPicker
import com.dsm.mediapicker.enum.PickerOrientation
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

    private val adapter: PostImageListAdapter by lazy { PostImageListAdapter(viewModel) }

    override fun viewInit() {
        rv_post_image.adapter = adapter

        tb_post_purchase.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivityForResult(Intent(this, PostCategoryActivity::class.java),
            CATEGORY
        ) }

        iv_select_image.setOnClickListener {
            if (PermissionUtil.isReadExternalStorageAllow(this)) {
                PermissionUtil.requestReadExternalStorage(this)
                Snackbar.make(iv_select_image, getString(R.string.fail_permission_denied), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            MediaPicker.createImage(this@PostPurchaseActivity)
                .maxImageCount(5)
                .theme(R.style.AppTheme)
                .toolbarBackgroundColor(R.color.colorPrimary)
                .toolbarTextColor(R.color.colorWhite)
                .orientation(PickerOrientation.PORTRAIT)
                .start(SELECT_IMAGE)
        }
    }

    override fun observeViewModel() {
        val `this` = this@PostPurchaseActivity
        viewModel.run {
            imageList.observe(`this`, Observer {
                if (it.size == 0) {
                    iv_select_image.visibility = View.VISIBLE
                } else {
                    adapter.setItems(it)
                    iv_select_image.visibility = View.INVISIBLE
                }
            })

            finishActivityEvent.observe(`this`, Observer { finish() })

            toastEvent.observe(`this`, Observer { toast(it) })

            showLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.show(supportFragmentManager) })

            hideLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.hide() })

            postPurchaseLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.POST_PURCHASE, it) })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PermissionUtil.requestReadExternalStorage(this)
        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE)
                viewModel.setImageList(arrayListOf<String>().apply {
                    MediaPicker.getResult(data).forEach { add(it) }
                })
            else if (requestCode == CATEGORY)
                viewModel.setCategory(data?.getStringExtra("category") ?: "")
        }
    }
}
