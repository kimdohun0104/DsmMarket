package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.CommentModel
import kotlinx.android.synthetic.main.item_comment.view.tv_content
import kotlinx.android.synthetic.main.item_comment.view.tv_date
import kotlinx.android.synthetic.main.item_comment.view.tv_name

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    private val listItems = arrayListOf<CommentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition]
            itemView.tv_name.text = item.writer
            itemView.tv_date.text = item.date
            itemView.tv_content.text = item.content
        }
    }

    fun addItems(items: List<CommentModel>) {
        items.forEach {
            listItems.add(it)
            notifyItemInserted(listItems.size - 1)
        }
    }
}