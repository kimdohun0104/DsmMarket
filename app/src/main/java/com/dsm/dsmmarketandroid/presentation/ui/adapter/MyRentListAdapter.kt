package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemMyPostBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostViewModel

class MyRentListAdapter(
    private val viewModel: MyPostViewModel
) : RecyclerView.Adapter<MyRentListAdapter.ViewHolder>() {

    private var listItems = listOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMyPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: List<ProductModel>) {
        listItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMyPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            binding.product = item
            binding.clParent.setOnClickListener { viewModel.intentRentDetail.value = item.postId }
            binding.ivEdit.setOnClickListener { viewModel.intentRentModify.value = item.postId }
        }
    }
}