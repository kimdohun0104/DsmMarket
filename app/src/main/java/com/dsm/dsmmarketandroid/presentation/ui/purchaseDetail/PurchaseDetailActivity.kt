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
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.DetailImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecommendListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.report.ReportPostDialog
import com.dsm.dsmmarketandroid.presentation.util.ProductType
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

        tb_purchase_detail.run {
            background.alpha = 0
            setNavigationOnClickListener { finish() }
            overflowIcon = ContextCompat.getDrawable(this@PurchaseDetailActivity, R.drawable.ic_menu)
        }

        rv_detail_image.run {
            (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
            adapter = DetailImageListAdapter(postId)
            addItemDecoration(LinePagerIndicatorDecoration())
            PagerSnapHelper().attachToRecyclerView(this)
        }

        val recommendListAdapter = RecommendListAdapter(ProductType.PURCHASE)
        (rv_recommend.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        rv_recommend.adapter = recommendListAdapter

        val relatedListAdapter = RecommendListAdapter(ProductType.PURCHASE)
        (rv_related.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        rv_related.adapter = relatedListAdapter

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to ProductType.PURCHASE) }

        btn_deal_with_chat.setOnClickListener { viewModel.createRoom(postId) }

        viewModel.getPurchaseDetail(postId)
        viewModel.getRelatedProduct(postId)
        viewModel.getRecommendProduct(postId)

        viewModel.isInterest.observe(this, Observer {
            if (it) tb_purchase_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_full_red)
            else tb_purchase_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_white)
        })

        viewModel.recommendList.observe(this, Observer { recommendListAdapter.setItems(it) })

        viewModel.relatedList.observe(this, Observer { relatedListAdapter.setItems(it) })

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.intentChatActivityEvent.observe(this, Observer { startActivity<ChatActivity>("bundle" to it) })

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
                ReportPostDialog().apply {
                    arguments = Bundle().apply {
                        putInt("post_id", postId)
                        putInt("type", ProductType.PURCHASE)
                    }
                    show(supportFragmentManager, "")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
