package com.dsm.dsmmarketandroid.presentation.ui.post.postRent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryActivity
import kotlinx.android.synthetic.main.activity_post_rent.*
import org.jetbrains.anko.startActivity

class PostRentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_rent)
        tb_post_rent.setNavigationOnClickListener { finish() }

        cl_category.setOnClickListener { startActivity<PostCategoryActivity>() }
    }
}
