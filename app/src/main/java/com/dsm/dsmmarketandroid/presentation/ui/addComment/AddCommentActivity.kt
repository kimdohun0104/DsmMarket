package com.dsm.dsmmarketandroid.presentation.ui.addComment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityAddCommentBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_comment.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCommentActivity : BaseActivity<ActivityAddCommentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_add_comment

    private val viewModel: AddCommentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_add_comment.setNavigationOnClickListener { finish() }
        binding.postId = intent.getIntExtra("post_id", -1)
        binding.type = intent.getIntExtra("type", -1)

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        binding.viewModel = viewModel
    }
}
