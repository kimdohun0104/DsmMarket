package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemProductBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentViewModel

class RecentListAdapter(
    private val productType: Int,
    private val viewModel: RecentViewModel
) : RecyclerView.Adapter<RecentListAdapter.ViewHolder>() {

    private var listItems = listOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: List<ProductModel>) {
        listItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            binding.product = item
            binding.clParent.setOnClickListener {
                if (productType == 0) viewModel.intentPurchaseDetail.value = item.postId
                else viewModel.intentRentDetail.value = item.postId
            }
        }
    }
}