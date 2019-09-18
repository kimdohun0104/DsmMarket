package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemCommentBinding
import com.dsm.dsmmarketandroid.presentation.model.CommentModel
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentViewModel

class CommentListAdapter(
    private val viewModel: CommentViewModel
) : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    private var listItems = listOf<CommentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            binding.comment = item
            binding.ivReport.setOnClickListener { viewModel.fragmentReportCommentEvent.call() }
        }
    }

    fun setItems(items: List<CommentModel>) {
        listItems = items
        notifyDataSetChanged()
    }
}