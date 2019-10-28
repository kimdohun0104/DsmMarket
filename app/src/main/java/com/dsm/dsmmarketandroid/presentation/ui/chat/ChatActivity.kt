package com.dsm.dsmmarketandroid.presentation.ui.chat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.model.ChatModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatListAdapter
import io.socket.client.IO
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject

class ChatActivity : AppCompatActivity() {

    private val adapter = ChatListAdapter()
    private val socket = IO.socket("https://dsm-market.ga")

    private val roomId: Int by lazy { intent.getBundleExtra("bundle")?.getInt("roomId") ?: 0 }
    private val email: String by lazy { intent.getBundleExtra("bundle")?.getString("email") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        if (!socket.connected()) {
            socket.connect()
        }
        tb_chat.setNavigationOnClickListener { finish() }

        rv_chat.adapter = adapter
        rv_chat.layoutManager = LinearLayoutManager(this).apply { stackFromEnd = true }

        socket.emit("joinRoom", JSONObject().apply {
            put("room", roomId)
            put("email", email)
        })

        socket.on("broadcastMessage", broadcastMessage)

        iv_send_chat.setOnClickListener {
            val msg = et_chat.text.toString().trim()
            socket.emit("sendMessage", JSONObject().apply { put("msg", msg) })
            adapter.addMyChatItem(ChatModel.MyChat(msg, ""))
        }
    }

    private val broadcastMessage = Emitter.Listener {
        runOnUiThread {
            val data = it[0] as JSONObject
            adapter.addForeignChatItem(ChatModel.ForeignChat(data.getString("msg"), ""))
        }
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

    override fun onDestroy() {
        if (socket.connected()) {
            socket.disconnect()
        }
        super.onDestroy()
    }
}
