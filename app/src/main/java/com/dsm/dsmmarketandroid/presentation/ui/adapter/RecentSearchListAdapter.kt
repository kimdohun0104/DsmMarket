package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.item_search_history.view.*

class RecentSearchListAdapter : RecyclerView.Adapter<RecentSearchListAdapter.ViewHolder>() {

    private val listItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.tv_search_word.text = listItems[adapterPosition]
        }
    }

    fun addItems(items: List<String>) {
        items.forEach {
            listItems.add(it)
            notifyItemInserted(listItems.size - 1)
        }
    }
}