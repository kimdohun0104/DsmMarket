package com.dsm.dsmmarketandroid.presentation.ui.chat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ChatModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatListAdapter
import kotlinx.android.synthetic.main.activity_chat.rv_chat
import kotlinx.android.synthetic.main.activity_chat.tb_chat

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        tb_chat.setNavigationOnClickListener { finish() }

        val adapter = ChatListAdapter()
        rv_chat.adapter = adapter
        adapter.addDateItem(ChatModel.Date("2019-03-04"))
        adapter.addMyChatItem(ChatModel.MyChat("안녕하세요 반갑습니다", "10:30"))
        adapter.addMyChatItem(ChatModel.MyChat("이것은 겁나 긴 텍스트 테스트 ㅁㄴㅇ럼ㄴ;ㅇ란ㄻ넝;리마넝ㄹ;ㅁㄴ아ㅓㄻ;나이ㅓㄹ;ㅁ나이러;민아ㅓㄹ;민아러;밍나러;밍나러;ㅁㅇ니ㅏ러;ㅁㅇ나ㅣ러;ㅁㄴ아ㅣ러;ㅁ니아러;ㅁ니아럼;ㅇㄴ러ㅏ", "10:30"))
        adapter.addForeignChatItem(ChatModel.ForeignChat("안녕하세요 반갑습니다", "10:30"))
        adapter.addForeignChatItem(ChatModel.ForeignChat("이것은 겁나 긴 텍스트 테스트 ㅁㄴ;ㅇ럼;ㅇ니람ㅇㄴㄹ;ㅣㅏㅁㅇㄹ;ㅇ냐ㅓㄹ;ㅁㄴ야러ㅏㅣㅁㅇ나러민아러민ㅇ라먼ㅇ라ㅣㅁ넝리망너리망너리ㅏㅁㅇ너리망널밍나러밍나러민아러민아럼니런ㅇ미라ㅓㅣㅏㅓ", "10:30"))

        (rv_chat.layoutManager as LinearLayoutManager).stackFromEnd = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_chat_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.report ->
//        }
        return super.onOptionsItemSelected(item)
    }
}
