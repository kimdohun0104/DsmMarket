package com.dsm.dsmmarketandroid.presentation.ui.comment.addComment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityAddCommentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import kotlinx.android.synthetic.main.activity_add_comment.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCommentActivity : BaseActivity<ActivityAddCommentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_add_comment

    private val viewModel: AddCommentViewModel by viewModel()

    override fun viewInit() {
        binding.postId = intent.getIntExtra("post_id", -1)
        binding.type = intent.getIntExtra("type", -1)

        tb_add_comment.setNavigationOnClickListener { finish() }
    }

    override fun observeViewModel() {
        val `this` = this@AddCommentActivity
        viewModel.run {
            finishActivityEvent.observe(`this`, Observer { finish() })

            toastEvent.observe(`this`, Observer { toast(it) })

            addCommentLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.ADD_COMMENT, it) })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
