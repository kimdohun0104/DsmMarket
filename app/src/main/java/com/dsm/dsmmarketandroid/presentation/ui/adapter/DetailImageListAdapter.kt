package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.purchaseImage.PurchaseImageActivity
import com.dsm.dsmmarketandroid.presentation.util.GlideApp
import kotlinx.android.synthetic.main.item_detail_image.view.*
import org.jetbrains.anko.startActivity

class DetailImageListAdapter(
    private val context: Context,
    private val postId: Int
) : RecyclerView.Adapter<DetailImageListAdapter.ViewHolder>() {

    var listItems = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_image, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            GlideApp.with(itemView).load(listItems[adapterPosition]).into(itemView.iv_image)

            itemView.setOnClickListener {
                context.startActivity<PurchaseImageActivity>("post_id" to postId)
            }
        }
    }
}