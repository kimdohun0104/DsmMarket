package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.item_post_image.view.*

// TODO modifyImage, postImage list adapter 생각해보기
class ModifyImageListAdapter : RecyclerView.Adapter<ModifyImageListAdapter.ViewHolder>() {

    private var listItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_modify_image, parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: ArrayList<String>) {
        listItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            Glide.with(itemView)
                .load(listItems[adapterPosition])
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_error)
                .into(itemView.iv_image)
        }
    }
}