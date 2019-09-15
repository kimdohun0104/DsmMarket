package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemSearchHistoryBinding
import com.dsm.dsmmarketandroid.presentation.model.SearchHistoryModel
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchViewModel

class SearchHistoryListAdapter(private val viewModel: SearchViewModel) : RecyclerView.Adapter<SearchHistoryListAdapter.ViewHolder>() {

    private var listItems = listOf<SearchHistoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: List<SearchHistoryModel>) {
        listItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            binding.searchHistoryModel = item
            binding.clParent.setOnClickListener {
                viewModel.onClickSearchHistory(item.content)
            }
            binding.ibDelete.setOnClickListener {
                viewModel.deleteSearchHistory(item.content)
            }
        }
    }
}