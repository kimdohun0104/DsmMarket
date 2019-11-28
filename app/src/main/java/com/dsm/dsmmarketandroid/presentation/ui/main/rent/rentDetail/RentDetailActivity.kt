package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRentDetailBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecommendListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.main.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.CommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent.ModifyRentActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentImage.RentImageActivity
import com.dsm.dsmmarketandroid.presentation.ui.report.ReportPostDialog
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import kotlinx.android.synthetic.main.activity_rent_detail.*
import kr.sdusb.libs.messagebus.MessageBus
import kr.sdusb.libs.messagebus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentDetailActivity : BaseActivity<ActivityRentDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_rent_detail

    private val viewModel: RentDetailViewModel by viewModel()

    private val postId by lazy { intent.getIntExtra("post_id", -1) }

    private val relatedListAdapter = RecommendListAdapter(ProductType.RENT, false)

    private val popup: PopupMenu by lazy { PopupMenu(this, iv_rent_detail_menu) }

    private var isMe = false

    override fun viewInit() {
        tb_rent_detail.setNavigationOnClickListener { finish() }

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to ProductType.RENT) }

        iv_rent_image.setOnClickListener { startActivity<RentImageActivity>("post_id" to postId) }

        btn_deal_with_chat.setOnClickListener { viewModel.createRoom(postId) }

        rv_related.adapter = relatedListAdapter

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.report -> {
                    ReportPostDialog().apply {
                        arguments = Bundle().apply {
                            putInt("post_id", postId)
                            putInt("type", ProductType.RENT)
                        }
                        show(supportFragmentManager, "")
                    }
                }
                R.id.modify -> {
                    startActivity<ModifyRentActivity>("post_id" to postId)
                }
            }
            true
        }

        iv_rent_detail_menu.setOnClickListener { popup.show() }

        iv_rent_detail_interest.setOnClickListener { viewModel.onClickInterest(postId) }

        viewModel.getRentDetail(postId)
        viewModel.getRelatedProduct(postId)
    }

    override fun observeViewModel() {
        val `this` = this@RentDetailActivity
        viewModel.run {
            relatedList.observe(`this`, Observer { relatedListAdapter.setItems(it) })

            toastEvent.observe(`this`, Observer { toast(it) })

            startChatActivityEvent.observe(`this`, Observer { startActivity<ChatActivity>("bundle" to it) })

            showLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.show(supportFragmentManager) })

            hideLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.hide() })

            interestLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.INTEREST_RENT, it) })

            rentDetailLogEvent.observe(`this`, Observer { Analytics.logEvent(`this`, Analytics.RENT_DETAIL, it) })

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

    @Subscribe(events = [MessageEvents.MODIFY_RENT_EVENT])
    fun modifyRentEvent() {
        viewModel.getRentDetail(postId)
    }

    @Subscribe(events = [MessageEvents.INCREASE_COMMENT_COUNT_EVENT])
    fun increaseCommentCountEvent() {
        tv_rent_comment_count.text = (tv_rent_comment_count.text.toString().toInt() + 1).toString()
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
