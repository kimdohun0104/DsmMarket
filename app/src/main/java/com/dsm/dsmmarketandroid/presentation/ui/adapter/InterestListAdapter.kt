package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemInterestBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailActivity
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity
import com.dsm.dsmmarketandroid.presentation.util.ProductType

class InterestListAdapter(private val type: Int) : RecyclerView.Adapter<InterestListAdapter.ViewHolder>() {

    private var listItems = listOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemInterestBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: List<ProductModel>) {
        listItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemInterestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            val context = binding.root.context
            binding.product = item
            binding.clParent.setOnClickListener {
                val intent =
                    if (type == ProductType.PURCHASE)
                        Intent(context, PurchaseDetailActivity::class.java)
                    else
                        Intent(context, RentDetailActivity::class.java)
                intent.putExtra("post_id", item.postId)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    context as Activity,
                    Pair.create(binding.ivProduct as View, "image"),
                    Pair.create(binding.tvTitle as View, "title")
                )
                context.startActivity(intent, options.toBundle())
            }
        }
    }
}