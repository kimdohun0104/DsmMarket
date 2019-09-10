package com.dsm.dsmmarketandroid.presentation.ui.comment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.CommentModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.CommentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentActivity
import kotlinx.android.synthetic.main.activity_comment.rv_comment
import kotlinx.android.synthetic.main.activity_comment.tb_comment
import org.jetbrains.anko.startActivity

class CommentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        tb_comment.setNavigationOnClickListener { finish() }
        setSupportActionBar(tb_comment)

        val adapter = CommentListAdapter()
        rv_comment.adapter = adapter
        adapter.addItems(
            listOf(
                CommentModel(
                    "소동현", "2019-03-01", "저 정말 지옥참마도 가지고 싶었는데...\n" +
                            "제가 통장에 돈이 999원 밖에 없네요... \n" +
                            "하... 엄마한테 돈이라도 빌려야하나...."
                ),
                CommentModel(
                    "소동현", "2019-03-01", "저 정말 지옥참마도 가지고 싶었는데...\n" +
                            "제가 통장에 돈이 999원 밖에 없네요... \n" +
                            "하... 엄마한테 돈이라도 빌려야하나...."
                ),
                CommentModel(
                    "소동현", "2019-03-01", "저 정말 지옥참마도 가지고 싶었는데...\n" +
                            "제가 통장에 돈이 999원 밖에 없네요... \n" +
                            "하... 엄마한테 돈이라도 빌려야하나...."
                ),
                CommentModel(
                    "소동현", "2019-03-01", "저 정말 지옥참마도 가지고 싶었는데...\n" +
                            "제가 통장에 돈이 999원 밖에 없네요... \n" +
                            "하... 엄마한테 돈이라도 빌려야하나...."
                )
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_comment_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_comment -> startActivity<AddCommentActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}
