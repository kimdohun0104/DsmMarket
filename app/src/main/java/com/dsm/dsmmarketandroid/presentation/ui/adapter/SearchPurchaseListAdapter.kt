package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsm.data.paging.NetworkState
import com.dsm.dsmmarketandroid.databinding.ItemProductBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.searchResult.SearchResultViewModel

class SearchPurchaseListAdapter(
    private val viewModel: SearchResultViewModel
) : PagedListAdapter<ProductModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean = oldItem.postId == newItem.postId

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean = oldItem == newItem
        }

        private const val TYPE_LOADING = 0
        private const val TYPE_ITEM = 1
    }

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ITEM -> ItemHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ItemHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is PurchaseListAdapter.LoadingHolder -> holder.bind()
            is PurchaseListAdapter.ItemHolder -> holder.bind(getItem(position))
            else -> (holder as ItemHolder).bind(getItem(position))
        }

    override fun getItemViewType(position: Int): Int =
        if (hasExtraRow() && position == itemCount - 1) TYPE_LOADING else TYPE_ITEM

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    inner class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    inner class ItemHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductModel?) {
            binding.product = item
            binding.clParent.setOnClickListener { viewModel.intentPurchaseDetail.value = item?.postId }
        }
    }
}