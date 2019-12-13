package com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityModifyRentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postCategory.PostCategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent.rentTime.ModifyRentTimeDialog
import com.dsm.dsmmarketandroid.presentation.util.retrySnackbar
import kotlinx.android.synthetic.main.activity_modify_rent.*
import kotlinx.android.synthetic.main.activity_post_rent.cl_category
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModifyRentActivity : BaseActivity<ActivityModifyRentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_modify_rent

    companion object {
        private const val CATEGORY = 1
    }

    private val postId: Int by lazy { intent.getIntExtra("post_id", -1) }
    private val viewModel: ModifyRentViewModel by viewModel()

    override fun viewInit() {
        binding.postId = postId

        tb_modify_rent.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener {
            startActivityForResult(
                Intent(this, PostCategoryActivity::class.java),
                CATEGORY
            )
        }

        btn_modify_time.setOnClickListener { ModifyRentTimeDialog().show(supportFragmentManager, "") }

        viewModel.getRentDetail(postId)
    }

    override fun observeViewModel() {
        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.snackbarRetryEvent.observe(this, Observer {
            retrySnackbar(cl_modify_rent_parent) { viewModel.getRentDetail(postId) }
        })
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
