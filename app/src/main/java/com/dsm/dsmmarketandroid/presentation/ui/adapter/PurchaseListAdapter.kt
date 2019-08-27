package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_interesting_product.view.iv_product
import kotlinx.android.synthetic.main.item_interesting_product.view.tv_product_date
import kotlinx.android.synthetic.main.item_interesting_product.view.tv_product_price
import kotlinx.android.synthetic.main.item_interesting_product.view.tv_title
import kotlinx.android.synthetic.main.item_product.view.*
import org.jetbrains.anko.startActivity

class PurchaseListAdapter : RecyclerView.Adapter<PurchaseListAdapter.ViewHolder>() {

    private val listItems = arrayListOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition]
            Picasso.get().load(item.imageUrl).into(itemView.iv_product)
            itemView.tv_title.text = item.title
            itemView.tv_product_date.text = item.postDate
            itemView.tv_product_price.text = item.price
            itemView.cl_parent.setOnClickListener { itemView.context.startActivity<PurchaseDetailActivity>() }
        }
    }

    fun addItems(items: List<ProductModel>) {
        items.forEach {
            listItems.add(it)
            notifyItemInserted(listItems.size - 1)
        }
    }
}