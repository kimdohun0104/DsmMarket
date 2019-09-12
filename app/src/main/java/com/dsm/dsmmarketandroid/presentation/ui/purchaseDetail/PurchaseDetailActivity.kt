package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityPurchaseDetailBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.DetailImagePagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentActivity
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

        vp_detail_image.adapter = DetailImagePagerAdapter()

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to 0) }

        btn_deal_with_chat.setOnClickListener { startActivity<ChatActivity>() }

        viewModel.getPurchaseDetail(postId)

        viewModel.toastNonExistEvent.observe(this, Observer { toast(getString(R.string.fail_non_exist_post)) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.isInterest.observe(this, Observer {
            if (it) tb_purchase_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_full_red)
            else tb_purchase_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_white)
        })

        viewModel.toastInterestEvent.observe(this, Observer { toast(getString(R.string.interest)) })

        viewModel.toastUnInterestEvent.observe(this, Observer { toast(getString(R.string.un_interest)) })

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.interest -> viewModel.onClickInterest(postId)
        }
        return super.onOptionsItemSelected(item)
    }
}
