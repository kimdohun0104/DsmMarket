package com.dsm.dsmmarketandroid.presentation.ui.chat

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.custom.EndlessRecyclerListener
import com.dsm.dsmmarketandroid.databinding.ActivityChatBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.model.ChatModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatListAdapter
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_chat

    private val adapter = ChatListAdapter()

    private val socket: Socket by lazy { IO.socket("https://dsm-market.ga") }

    private val roomId: Int by lazy { intent.getBundleExtra("bundle")?.getInt("roomId") ?: 0 }
    private val email: String by lazy { intent.getBundleExtra("bundle")?.getString("email") ?: "" }
    private val roomTitle: String by lazy { intent.getBundleExtra("bundle")?.getString("roomTitle") ?: "" }

    private val viewModel: ChatViewModel by viewModel()

    override fun viewInit() {
        tb_chat.setNavigationOnClickListener { finish() }
        tb_chat.title = roomTitle

        rv_chat.adapter = adapter
        (rv_chat.layoutManager as LinearLayoutManager).reverseLayout = true

        rv_chat.addOnScrollListener(object : EndlessRecyclerListener() {
            override fun onLoadMore(currentPage: Int) {
                viewModel.loadChatLog(roomId, currentPage)
            }
        })

        iv_send_chat.setOnClickListener {
            val msg = et_chat.text.toString().trim()
            if (msg.isNotBlank()) {
                val time = SimpleDateFormat("HH:mm", Locale.KOREA).format(Date(System.currentTimeMillis()))
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date(System.currentTimeMillis()))
                adapter.addDate(date)
                socket.emit("sendMessage", JSONObject().apply {
                    put("msg", msg)
                    put("time", time)
                })
                adapter.addMyChatItem(ChatModel.MyChat(msg, time, date))
                et_chat.setText("")
                rv_chat.scrollToPosition(0)
            }
        }

        et_chat.setEditorActionListener(EditorInfo.IME_ACTION_SEND) { iv_send_chat.performClick() }

        viewModel.loadChatLog(roomId, 0)
    }

    override fun observeViewModel() {
        viewModel.newItems.observe(this, Observer { adapter.addItems(it) })

        viewModel.showLoadingItemEvent.observe(this, Observer { adapter.showLoading() })

        viewModel.hideLoadingItemEvent.observe(this, Observer { adapter.hideLoading() })
    }

    private fun socketInit() {
        socket.connect()

        socket.emit("joinRoom", JSONObject().apply {
            put("room", roomId)
            put("email", email)
        })

        socket.on("broadcastMessage", broadcastMessage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        socketInit()
    }

    private val broadcastMessage = Emitter.Listener {
        runOnUiThread {
            val data = it[0] as JSONObject
            adapter.addForeignChatItem(ChatModel.ForeignChat(data.getString("msg"), data.getString("time"), ""))
            rv_chat.scrollToPosition(0)
        }
    }

    override fun onDestroy() {
        socket.disconnect()
        super.onDestroy()
    }
}
