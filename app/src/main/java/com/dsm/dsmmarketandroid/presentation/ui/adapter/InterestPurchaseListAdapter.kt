package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ProductModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_interesting_product.view.*

class InterestPurchaseListAdapter : RecyclerView.Adapter<InterestPurchaseListAdapter.ViewHolder>() {

    private val listItems = arrayListOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_interesting_product, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition]
            Picasso.get().load(item.imageUrl).into(itemView.iv_product)
            itemView.tv_title.text = item.title
            itemView.tv_product_date.text = item.postDate
            itemView.tv_product_price.text = item.price
        }
    }

    fun addItems(items: List<ProductModel>) {
        items.forEach {
            listItems.add(it)
            notifyItemInserted(listItems.size - 1)
        }
    }
}