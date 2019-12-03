package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsm.data.paging.NetworkState
import com.dsm.dsmmarketandroid.databinding.ItemLoadingBinding
import com.dsm.dsmmarketandroid.databinding.ItemProductBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail.PurchaseDetailActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail.RentDetailActivity
import com.dsm.dsmmarketandroid.presentation.util.ProductType

class ProductListAdapter(private val type: Int) : PagedListAdapter<ProductModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        var DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean =
                oldItem.postId == newItem.postId

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean =
                oldItem == newItem

        }

        private const val TYPE_LOADING = 0
        private const val TYPE_ITEM = 1
    }

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ITEM -> ItemHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> LoadingHolder(ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) holder.bind(getItem(position))
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

    inner class LoadingHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)

    inner class ItemHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductModel?) {
            binding.run {
                val context = root.context
                product = item

                root.setOnClickListener {
                    val intent =
                        if (type == ProductType.PURCHASE) Intent(context, PurchaseDetailActivity::class.java)
                        else Intent(context, RentDetailActivity::class.java)
                    intent.putExtra("post_id", item?.postId)
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                        context as Activity,
                        Pair.create(ivProductThumb as View, "image"),
                        Pair.create(tvProductTitle as View, "title")
                    )
                    context.startActivity(intent, options.toBundle())
                }
            }
        }
    }
}