package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ChatRoomModel
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatActivity
import com.dsm.dsmmarketandroid.presentation.util.GlideApp
import kotlinx.android.synthetic.main.item_chat_room.view.*
import org.jetbrains.anko.startActivity

class ChatRoomListAdapter : RecyclerView.Adapter<ChatRoomListAdapter.ViewHolder>() {

    private val listItems = arrayListOf<ChatRoomModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_room, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition]
            GlideApp.with(itemView).load(item.imageUrl).into(itemView.iv_chat_room)
            itemView.tv_chat_room_name.text = item.title
            itemView.tv_chat_last_message.text = item.lastMessage
            itemView.cl_chat_room.setOnClickListener { itemView.context.startActivity<ChatActivity>() }
        }
    }

    fun addItems(items: List<ChatRoomModel>) {
        items.forEach {
            listItems.add(it)
            notifyItemInserted(listItems.size - 1)
        }
    }
}