package com.dsm.dsmmarketandroid.presentation.ui.chatList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentChatListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatRoomListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : BaseFragment<FragmentChatListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_chat_list

    private val viewModel: ChatListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChatRoomListAdapter(viewModel)
        rv_chat_room.adapter = adapter

        srl_chat.setOnRefreshListener { viewModel.getChatRoom() }

        viewModel.getChatRoom()

        viewModel.chatRoomList.observe(this, Observer { adapter.addItems(it) })

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.intentChatActivityEvent.observe(this, Observer { startActivity<ChatActivity>("bundle" to it) })

        viewModel.hideChatRefreshEvent.observe(this, Observer { srl_chat.isRefreshing = false })
    }
}