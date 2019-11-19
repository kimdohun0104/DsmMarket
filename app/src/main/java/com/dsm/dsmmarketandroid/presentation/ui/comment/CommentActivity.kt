package com.dsm.dsmmarketandroid.presentation.ui.comment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityCommentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.CommentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.report.ReportCommentDialog
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import kotlinx.android.synthetic.main.activity_comment.*
import kr.sdusb.libs.messagebus.MessageBus
import kr.sdusb.libs.messagebus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentActivity : BaseActivity<ActivityCommentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_comment

    private val postId: Int by lazy { intent.getIntExtra("post_id", -1) }
    private val type: Int by lazy { intent.getIntExtra("type", -1) }

    private val viewModel: CommentViewModel by viewModel()

    private val adapter: CommentListAdapter by lazy { CommentListAdapter(viewModel) }

    override fun viewInit() {
        setSupportActionBar(tb_comment_list)
        tb_comment_list.setNavigationOnClickListener { finish() }

        rv_comment.adapter = adapter

        srl_comment.setOnRefreshListener { viewModel.getCommentList(postId, type) }

        viewModel.getCommentList(postId, type)
    }

    override fun observeViewModel() {
        viewModel.listItems.observe(this, Observer { adapter.setItems(it) })

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.dialogReportComment.observe(this, Observer {
            ReportCommentDialog().apply {
                arguments = Bundle().apply {
                    putInt("post_id", postId)
                    putInt("type", type)
                    putString("nick", it)
                }
                show(supportFragmentManager, "")
            }
        })

        viewModel.hideRefreshEvent.observe(this, Observer { srl_comment.isRefreshing = false })

        viewModel.getCommentLogEvent.observe(this, Observer { Analytics.logEvent(this, Analytics.GET_COMMENT, it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessageBus.getInstance().register(this)
        binding.viewModel = viewModel
    }

    @Subscribe(events = [MessageEvents.ADD_COMMENT_EVENT])
    fun onCommentAdded() {
        viewModel.getCommentList(postId, type)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_comment_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_comment -> startActivity<AddCommentActivity>("post_id" to postId, "type" to type)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        MessageBus.getInstance().unregister(this)
        super.onDestroy()
    }
}

