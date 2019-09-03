package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseViewModel
import kotlinx.android.synthetic.main.item_post_image.view.*

class PostImageListAdapter(private val viewModel: PostPurchaseViewModel) : RecyclerView.Adapter<PostImageListAdapter.ViewHolder>() {

    private var listItems = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_image, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.iv_image.setImageURI(listItems[adapterPosition])
            itemView.iv_delete_image.setOnClickListener {
                viewModel.imageRemovedAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    fun setItems(items: ArrayList<Uri>) {
        listItems = items
        notifyDataSetChanged()
    }
}