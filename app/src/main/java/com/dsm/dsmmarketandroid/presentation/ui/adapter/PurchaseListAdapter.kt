package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsm.data.paging.NetworkState
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.model.PurchaseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class PurchaseListAdapter : PagedListAdapter<PurchaseModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        var DIFF_CALLBACK = object : DiffUtil.ItemCallback<PurchaseModel>() {
            override fun areItemsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean =
                oldItem.postId == newItem.postId

            override fun areContentsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean =
                oldItem == newItem

        }

        private const val TYPE_LOADING = 0
        private const val TYPE_ITEM = 1
    }

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ITEM -> ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
            else -> ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is LoadingHolder -> holder.bind()
            is ItemHolder -> holder.bind(getItem(position))
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

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PurchaseModel?) {
            Picasso.get().load(item?.img).into(itemView.iv_product)
            itemView.tv_title.text = item?.title
            itemView.tv_product_date.text = item?.createdAt
            itemView.tv_product_price.text = item?.price
        }
    }
}