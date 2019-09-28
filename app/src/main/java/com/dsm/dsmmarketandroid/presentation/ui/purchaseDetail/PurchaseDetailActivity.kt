package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.custom.LinePagerIndicatorDecoration
import com.dsm.dsmmarketandroid.databinding.ActivityPurchaseDetailBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.DetailImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecommendListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.report.ReportPostDialog
import kotlinx.android.synthetic.main.activity_purchase_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PurchaseDetailActivity : BaseActivity<ActivityPurchaseDetailBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_purchase_detail

    private val viewModel: PurchaseDetailViewModel by viewModel()

    private val postId: Int by lazy { intent.getIntExtra("post_id", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(tb_purchase_detail)
        tb_purchase_detail.background.alpha = 0
        tb_purchase_detail.setNavigationOnClickListener { finish() }
        tb_purchase_detail.overflowIcon = ContextCompat.getDrawable(this, R.drawable.ic_menu)

        rv_detail_image.adapter = DetailImageListAdapter(this, postId)
        (rv_detail_image.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        PagerSnapHelper().attachToRecyclerView(rv_detail_image)
        rv_detail_image.addItemDecoration(LinePagerIndicatorDecoration())

        val recommendListAdapter = RecommendListAdapter(this, 0)
        (rv_recommend.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        rv_recommend.adapter = recommendListAdapter

        val relatedListAdapter = RecommendListAdapter(this, 0)
        (rv_related.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        rv_related.adapter = relatedListAdapter

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to 0) }

        btn_deal_with_chat.setOnClickListener { startActivity<ChatActivity>() }

        viewModel.getPurchaseDetail(postId)
        viewModel.getRecommendProduct(postId)
        viewModel.getRelatedProduct(postId)

        viewModel.toastNonExistEvent.observe(this, Observer { toast(getString(R.string.fail_non_exist_post)) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.isInterest.observe(this, Observer {
            if (it) tb_purchase_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_full_red)
            else tb_purchase_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_white)
        })

        viewModel.toastInterestEvent.observe(this, Observer { toast(getString(R.string.interest)) })

        viewModel.toastUnInterestEvent.observe(this, Observer { toast(getString(R.string.un_interest)) })

        viewModel.recommendList.observe(this, Observer { recommendListAdapter.setItems(it) })

        viewModel.relatedList.observe(this, Observer { relatedListAdapter.setItems(it) })

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.interest -> viewModel.onClickInterest(postId)
            R.id.report -> {
                val args = Bundle()
                args.putInt("post_id", postId)
                args.putInt("type", 0)
                val fragment = ReportPostDialog()
                fragment.arguments = args
                fragment.show(supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
