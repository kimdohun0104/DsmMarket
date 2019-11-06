package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemChatRoomBinding
import com.dsm.dsmmarketandroid.presentation.model.ChatRoomModel
import com.dsm.dsmmarketandroid.presentation.ui.chatList.ChatListViewModel

class ChatRoomListAdapter(private val viewModel: ChatListViewModel) : RecyclerView.Adapter<ChatRoomListAdapter.ChatRoomHolder>() {

    private val listItems = arrayListOf<ChatRoomModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomHolder =
        ChatRoomHolder(ItemChatRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ChatRoomHolder, position: Int) = holder.bind()

    fun addItems(items: List<ChatRoomModel>) {
        listItems.clear()
        items.forEach { listItems.add(it) }
        notifyDataSetChanged()
    }

    inner class ChatRoomHolder(private val binding: ItemChatRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.chatRoom = listItems[adapterPosition]
            binding.root.setOnClickListener {
                viewModel.joinRoom(listItems[adapterPosition].roomId, listItems[adapterPosition].roomName)
            }
        }
    }
}