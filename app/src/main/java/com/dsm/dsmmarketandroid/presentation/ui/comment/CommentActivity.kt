package com.dsm.dsmmarketandroid.presentation.ui.comment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityCommentBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.CommentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_comment.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentActivity : BaseActivity<ActivityCommentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_comment

    private val viewModel: CommentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_comment.setNavigationOnClickListener { finish() }
        setSupportActionBar(tb_comment)
        val postId = intent.getIntExtra("post_id", -1)
        val type = intent.getIntExtra("type", -1)

        val adapter = CommentListAdapter()
        rv_comment.adapter = adapter

        viewModel.getCommentList(postId, type)

        viewModel.listItems.observe(this, Observer { adapter.setItems(it) })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_comment_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_comment -> startActivity<AddCommentActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}
