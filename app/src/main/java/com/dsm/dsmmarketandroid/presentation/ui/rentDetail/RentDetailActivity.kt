package com.dsm.dsmmarketandroid.presentation.ui.rentDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityRentDetailBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecommendListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentActivity
import com.dsm.dsmmarketandroid.presentation.ui.report.ReportPostDialog
import kotlinx.android.synthetic.main.activity_rent_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentDetailActivity : BaseActivity<ActivityRentDetailBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_rent_detail

    private val viewModel: RentDetailViewModel by viewModel()

    private val postId by lazy { intent.getIntExtra("post_id", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(tb_rent_detail)
        tb_rent_detail.background.alpha = 0
        tb_rent_detail.setNavigationOnClickListener { finish() }
        tb_rent_detail.overflowIcon = ContextCompat.getDrawable(this, R.drawable.ic_menu)

        ll_comment.setOnClickListener { startActivity<CommentActivity>("post_id" to postId, "type" to 1) }

        val relatedListAdapter = RecommendListAdapter(this, 1)
        (rv_related.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        rv_related.adapter = relatedListAdapter

        viewModel.getRentDetail(postId)
        viewModel.getRelatedProduct(postId)

        viewModel.isInterest.observe(this, Observer {
            if (it) tb_rent_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_full_red)
            else tb_rent_detail.menu[0].icon = getDrawable(R.drawable.ic_heart_white)
        })

        viewModel.toastInterestEvent.observe(this, Observer { toast(getString(R.string.interest)) })

        viewModel.toastUnInterestEvent.observe(this, Observer { toast(getString(R.string.un_interest)) })

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
                args.putInt("type", 1)
                val fragment = ReportPostDialog()
                fragment.arguments = args
                fragment.show(supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
