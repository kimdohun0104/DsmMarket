package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemRecommendBinding
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailActivity
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity
import org.jetbrains.anko.startActivity

// TODO 관련 상품과 추천 상품이 공통으로 사용하는 Adapter인데 명확하지가 않음
class RecommendListAdapter(
    private val context: Context,
    private val type: Int
) : RecyclerView.Adapter<RecommendListAdapter.ViewHolder>() {

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
            binding.clParent.setOnClickListener {
                if (type == 0) context.startActivity<PurchaseDetailActivity>("post_id" to item.postId)
                else context.startActivity<RentDetailActivity>("post_id" to item.postId)
            }
        }
    }
}