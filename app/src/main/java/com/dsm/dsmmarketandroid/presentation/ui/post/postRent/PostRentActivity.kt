package com.dsm.dsmmarketandroid.presentation.ui.post.postRent

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostRentBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime.SelectRentTimeFragment
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import com.dsm.dsmmarketandroid.presentation.util.PermissionUtil
import com.esafirm.imagepicker.features.ImagePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post_rent.btn_select_time
import kotlinx.android.synthetic.main.activity_post_rent.cl_category
import kotlinx.android.synthetic.main.activity_post_rent.iv_select_image
import kotlinx.android.synthetic.main.activity_post_rent.tb_post_rent
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostRentActivity : BaseActivity<ActivityPostRentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_post_rent

    companion object {
        private const val SELECT_IMAGE = 1
        private const val CATEGORY = 2
    }

    private val viewModel: PostRentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PermissionUtil.requestReadExternalStorage(this)

        tb_post_rent.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivityForResult(Intent(this, PostCategoryActivity::class.java), CATEGORY) }

        iv_select_image.setOnClickListener {
            if (PermissionUtil.isReadExternalStorageAllow(this)) {
                PermissionUtil.requestReadExternalStorage(this)
                Snackbar.make(iv_select_image, getString(R.string.fail_permission_denied), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            ImagePicker.create(this@PostRentActivity).single().start(SELECT_IMAGE)
        }

        btn_select_time.setOnClickListener { SelectRentTimeFragment().show(supportFragmentManager, "") }

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                val imagePath = ImagePicker.getFirstImageOrNull(data).path
                iv_select_image.setImageBitmap(BitmapFactory.decodeFile(imagePath))
                viewModel.photo.value = imagePath
            } else if (requestCode == CATEGORY) {
                viewModel.category.value = data?.getStringExtra("category")
            }
        }
    }
}
