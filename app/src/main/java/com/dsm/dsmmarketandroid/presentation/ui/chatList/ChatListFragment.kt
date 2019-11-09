package com.dsm.dsmmarketandroid.presentation.ui.chatList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentChatListBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragmentRefac
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ChatRoomListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.util.LoadingDialog
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : BaseFragmentRefac<FragmentChatListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_chat_list

    private val viewModel: ChatListViewModel by viewModel()

    private val adapter: ChatRoomListAdapter by lazy { ChatRoomListAdapter(viewModel) }

    override fun initView() {
        rv_chat_room.adapter = adapter

        srl_chat.setOnRefreshListener { viewModel.getChatRoom() }

        viewModel.getChatRoom()
    }

    override fun observeViewModel() {
        viewModel.chatRoomList.observe(this, Observer { adapter.setItems(it) })

        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.intentChatActivityEvent.observe(this, Observer { startActivity<ChatActivity>("bundle" to it) })

        viewModel.showLoadingDialogEvent.observe(this, Observer { LoadingDialog.show(childFragmentManager) })

        viewModel.hideLoadingDialogEvent.observe(this, Observer { LoadingDialog.hide() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}