package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemRecommendBinding
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailActivity
import org.jetbrains.anko.startActivity

class RecommendListAdapter(private val context: Context) : RecyclerView.Adapter<RecommendListAdapter.ViewHolder>() {

    private var listItems = listOf<RecommendModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: List<RecommendModel>) {
        listItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRecommendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            binding.recommend = item
            binding.clParent.setOnClickListener { context.startActivity<PurchaseDetailActivity>("post_id" to item.postId) }
        }
    }
}