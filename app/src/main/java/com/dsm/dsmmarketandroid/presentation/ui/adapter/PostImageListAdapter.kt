package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_image.view.iv_delete_image
import kotlinx.android.synthetic.main.item_post_image.view.iv_image
import java.io.File

class PostImageListAdapter(private val viewModel: PostPurchaseViewModel) : RecyclerView.Adapter<PostImageListAdapter.ViewHolder>() {

    private var listItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_image, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            Picasso.get().load(File(listItems[adapterPosition])).into(itemView.iv_image)
            itemView.iv_delete_image.setOnClickListener {
                viewModel.imageRemovedAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    fun setItems(items: ArrayList<String>) {
        listItems = items
        notifyDataSetChanged()
    }
}