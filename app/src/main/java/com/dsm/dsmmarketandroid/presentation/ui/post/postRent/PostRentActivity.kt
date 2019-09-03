package com.dsm.dsmmarketandroid.presentation.ui.post.postRent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPostRentBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.rentTime.SelectRentTimeFragment
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post_rent.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostRentActivity : BaseActivity<ActivityPostRentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_post_rent

    companion object {
        private const val READ_EXTERNAL_STORAGE_PERMISSION = 0
        private const val SELECT_IMAGE = 1
    }

    private val viewModel: PostRentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionRequest()

        tb_post_rent.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivity<PostCategoryActivity>() }

        iv_select_image.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                permissionRequest()
                Snackbar.make(iv_select_image, getString(R.string.fail_permission_denied), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, SELECT_IMAGE)
        }

        btn_select_time.setOnClickListener { SelectRentTimeFragment().show(supportFragmentManager, "") }

        binding.viewModel = viewModel
    }

    private fun permissionRequest() =
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                iv_select_image.setImageURI(data?.data!!)
                viewModel.photo.value = getPathFromUri(data.data!!)
            }
        }
    }

    private fun getPathFromUri(contentUri: Uri): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(columnIndex)
        }
        cursor.close()
        return res
    }
}
