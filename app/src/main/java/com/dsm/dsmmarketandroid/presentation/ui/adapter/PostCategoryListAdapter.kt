package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.model.PostCategoryModel
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postCategory.PostCategoryViewModel
import kotlinx.android.synthetic.main.item_child_category.view.*
import kotlinx.android.synthetic.main.item_parent_category.view.*

class PostCategoryListAdapter(private val viewModel: PostCategoryViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val PARENT_CATEGORY = 0
        private const val CHILD_CATEGORY = 1
    }

    private val listItems = arrayListOf<PostCategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PARENT_CATEGORY) {
            ParentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_parent_category, parent, false))
        } else {
            ChildViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_child_category, parent, false))
        }
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ParentViewHolder -> {
                val item = listItems[position] as PostCategoryModel.ParentCategory
                holder.bind()
                if (item.invisibleChildren == null) {
                    holder.ivExpand.setImageResource(R.drawable.ic_up_arrow_grey)
                } else {
                    holder.ivExpand.setImageResource(R.drawable.ic_down_arrow_grey)
                }
                holder.clParent.setOnClickListener {
                    if (item.invisibleChildren == null) {
                        item.invisibleChildren = arrayListOf()
                        var count = 0
                        val pos = listItems.indexOf(item)
                        while (listItems.size > pos + 1 && listItems[pos + 1] is PostCategoryModel.ChildCategory) {
                            (item.invisibleChildren as ArrayList<PostCategoryModel.ChildCategory>).add(listItems.removeAt(pos + 1) as PostCategoryModel.ChildCategory)
                            count++
                        }
                        notifyItemRangeRemoved(pos + 1, count)
                        holder.ivExpand.setImageResource(R.drawable.ic_down_arrow_grey)
                    } else {
                        val pos = listItems.indexOf(item)
                        var index = pos + 1
                        item.invisibleChildren?.forEach {
                            listItems.add(index, it)
                            index++
                        }
                        notifyItemRangeInserted(pos + 1, index - pos - 1)
                        holder.ivExpand.setImageResource(R.drawable.ic_up_arrow_grey)
                        item.invisibleChildren = null
                    }
                }
            }
            is ChildViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listItems[position]) {
            is PostCategoryModel.ParentCategory -> PARENT_CATEGORY
            is PostCategoryModel.ChildCategory -> CHILD_CATEGORY
        }
    }

    fun addItems(items: List<PostCategoryModel>) {
        items.forEach { listItems.add(it) }
        notifyDataSetChanged()
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivExpand: ImageView = itemView.findViewById(R.id.iv_expand)
        val clParent: ConstraintLayout = itemView.findViewById(R.id.cl_parent)
        fun bind() {
            val item = listItems[adapterPosition] as PostCategoryModel.ParentCategory
            itemView.tv_parent_category.text = item.category
        }
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition] as PostCategoryModel.ChildCategory
            itemView.tv_child_category.text = item.category
            itemView.cl_child_parent.setOnClickListener {
                viewModel.selectCategory(item.parent + "/" + item.category)
            }
        }
    }
}