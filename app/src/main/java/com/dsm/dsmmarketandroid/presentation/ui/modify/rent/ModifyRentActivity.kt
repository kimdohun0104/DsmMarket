package com.dsm.dsmmarketandroid.presentation.ui.modify.rent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityModifyRentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.rentTime.ModifyRentTimeFragment
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
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

    private val viewModel: ModifyRentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postId = intent.getIntExtra("post_id", -1)
        binding.postId = postId
        tb_modify_rent.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivityForResult(Intent(this, PostCategoryActivity::class.java), CATEGORY) }

        btn_modify_time.setOnClickListener { ModifyRentTimeFragment().show(supportFragmentManager, "") }

        viewModel.getRentDetail(postId)

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

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
