package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemProductBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailActivity
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity

// TODO ProductType같은거 Enum이라도 만들어서 써야할듯
class RecentListAdapter(
    private val productType: Int,
    private val context: Context
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
                val intent =
                    if (productType == 0) Intent(context, PurchaseDetailActivity::class.java)
                    else Intent(context, RentDetailActivity::class.java)
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