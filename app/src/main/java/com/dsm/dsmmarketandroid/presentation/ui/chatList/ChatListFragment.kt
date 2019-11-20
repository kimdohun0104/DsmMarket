package com.dsm.dsmmarketandroid.presentation.ui.chatList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentChatListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatRoomListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : BaseFragment<FragmentChatListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_chat_list

    private val viewModel: ChatListViewModel by viewModel()

    private val adapter: ChatRoomListAdapter by lazy { ChatRoomListAdapter(viewModel) }

    override fun viewInit() {
        rv_chat_room.adapter = adapter

        srl_chat.setOnRefreshListener { viewModel.getChatRoom() }

        viewModel.getChatRoom()
    }

    override fun observeViewModel() {
        val `this` = this@ChatListFragment
        viewModel.run {
            chatRoomList.observe(`this`, Observer { adapter.setItems(it) })

            toastEvent.observe(`this`, Observer { toast(it) })

            intentChatActivityEvent.observe(`this`, Observer { startActivity<ChatActivity>("bundle" to it) })

            showLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.show(childFragmentManager) })

            hideLoadingDialogEvent.observe(`this`, Observer { LoadingDialog.hide() })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}