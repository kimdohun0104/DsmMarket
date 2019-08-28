package com.dsm.dsmmarketandroid.presentation.ui.addComment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.activity_add_comment.*

class AddCommentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)
        tb_add_comment.setNavigationOnClickListener { finish() }
    }
}
