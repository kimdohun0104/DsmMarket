package com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.custom.LinePagerIndicatorDecoration
import com.dsm.dsmmarketandroid.databinding.ActivityPurchaseDetailBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.DetailImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecommendListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.main.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.CommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.modifyPurchase.ModifyPurchaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.report.ReportPostDialog
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.activity_purchase_detail.*
import kr.sdusb.libs.messagebus.MessageBus
import kr.sdusb.libs.messagebus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PurchaseDetailActivity : BaseActivity<ActivityPurchaseDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_purchase_detail

    private val viewModel: PurchaseDetailViewModel by viewModel()

    private val postId: Int by lazy { intent.getIntExtra("post_id", -1) }

    private val recommendListAdapter = RecommendListAdapter(ProductType.PURCHASE, true)
    private val relatedListAdapter = RecommendListAdapter(ProductType.PURCHASE, false)

    private val popup: PopupMenu by lazy { PopupMenu(this, iv_purchase_detail_menu) }

    private var isMe = false

    override fun viewInit() {
        tb_purchase_detail.setNavigationOnClickListener { finish() }

        rv_detail_image.run {
            adapter = DetailImageListAdapter()
            addItemDecoration(LinePagerIndicatorDecoration())
            PagerSnapHelper().attachToRecyclerView(this)
        }

        rv_recommend.adapter = recommendListAdapter
        rv_related.adapter = relatedListAdapter

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to ProductType.PURCHASE) }

        btn_deal_with_chat.setOnClickListener { viewModel.createRoom(postId) }

        iv_purchase_detail_interest.setOnClickListener { viewModel.onClickInterest(postId) }

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.report -> {
                    ReportPostDialog().apply {
                        arguments = Bundle().apply {
                            putInt("post_id", postId)
                            putInt("type", ProductType.PURCHASE)
                        }
                        show(supportFragmentManager, "")
                    }
                }
                R.id.modify -> {
                    startActivity<ModifyPurchaseActivity>("post_id" to postId)
                }
            }
            true
        }

        iv_purchase_detail_menu.setOnClickListener { popup.show() }

        viewModel.run {
            getPurchaseDetail(postId)
            getRelatedProduct(postId)
            getRecommendProduct()
        }
    }

    override fun observeViewModel() {
        val `this` = this@PurchaseDetailActivity
        viewModel.run {
            recommendList.observe(`this`, Observer { recommendListAdapter.setItems(it) })

            relatedList.observe(`this`, Observer { relatedListAdapter.setItems(it) })

            toastEvent.observe(`this`, Observer { toast(it) })

            finishActivityEvent.observe(`this`, Observer { finish() })

            intentChatActivityEvent.observe(`this`, Observer { startActivity<ChatActivity>("bundle" to it) })

            showLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.show(supportFragmentManager) })

            hideLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.hide() })

            purchaseDetailLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.PURCHASE_DETAIL, it) })

            interestLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.INTEREST_PURCHASE, it) })

            createChatRoomLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.CREATE_CHAT_ROOM, it) })

            isMe.observe(`this`, Observer {
                popup.menu.clear()
                if (it) {
                    `this`.isMe = true
                    popup.menuInflater.inflate(R.menu.menu_my_product_detail_toolbar, popup.menu)
                }
                else popup.menuInflater.inflate(R.menu.menu_product_detail_toolbar, popup.menu)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessageBus.getInstance().register(this)
        binding.viewModel = viewModel
    }

    @Subscribe(events = [MessageEvents.MODIFY_PURCHASE_EVENT])
    fun modifyPurchaseEvent() {
        viewModel.getPurchaseDetail(postId)
    }

    @Subscribe(events = [MessageEvents.INCREASE_COMMENT_COUNT_EVENT])
    fun increaseCommentCountEvent() {
        tv_purchase_comment_count.text = (tv_purchase_comment_count.text.toString().toInt() + 1).toString()
    }

    override fun onResume() {
        if (isMe) btn_deal_with_chat.visibility = View.GONE
        super.onResume()
    }

    override fun onDestroy() {
        MessageBus.getInstance().unregister(this)
        super.onDestroy()
    }
}
