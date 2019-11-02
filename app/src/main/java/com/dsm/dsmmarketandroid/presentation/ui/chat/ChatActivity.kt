package com.dsm.dsmmarketandroid.presentation.ui.chat

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.model.ChatModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatListAdapter
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity() {

    private val adapter = ChatListAdapter()

    private val socket: Socket by lazy { IO.socket("https://dsm-market.ga") }

    private val roomId: Int by lazy { intent.getBundleExtra("bundle")?.getInt("roomId") ?: 0 }
    private val email: String by lazy { intent.getBundleExtra("bundle")?.getString("email") ?: "" }
    private val roomTitle: String by lazy { intent.getBundleExtra("bundle")?.getString("roomTitle") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        tb_chat.title = roomTitle
        socket.connect()

        socket.emit("joinRoom", JSONObject().apply {
            put("room", roomId)
            put("email", email)
        })

        socket.on("broadcastMessage", broadcastMessage)

        tb_chat.setNavigationOnClickListener { finish() }

        iv_send_chat.setOnClickListener {
            val msg = et_chat.text.toString().trim()
            if (msg.isNotBlank()) {
                val time = SimpleDateFormat("H:mm", Locale.KOREA).format(Date(System.currentTimeMillis()))
                socket.emit("sendMessage", JSONObject().apply {
                    put("msg", msg)
                    put("time", time)
                })
                adapter.addMyChatItem(ChatModel.MyChat(msg, time))
                et_chat.setText("")
                rv_chat.scrollToPosition(adapter.listItems.size - 1)
            }
        }

        et_chat.setEditorActionListener(EditorInfo.IME_ACTION_SEND) { iv_send_chat.performClick() }

        rv_chat.adapter = adapter
        (rv_chat.layoutManager as LinearLayoutManager).stackFromEnd = true
    }

    private val broadcastMessage = Emitter.Listener {
        runOnUiThread {
            val data = it[0] as JSONObject
            adapter.addForeignChatItem(ChatModel.ForeignChat(data.getString("msg"), data.getString("time")))
            rv_chat.scrollToPosition(adapter.listItems.size - 1)
        }
    }

    override fun onDestroy() {
        socket.disconnect()
        super.onDestroy()
    }
}
