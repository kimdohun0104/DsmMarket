package com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.category.CategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import kotlinx.android.synthetic.main.activity_post_purchase.*
import org.jetbrains.anko.startActivity

class PostPurchaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_purchase)
        tb_post_purchase.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivity<PostCategoryActivity>() }
    }
}
