package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemMyPostBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.modify.ModifyRentActivity
import com.dsm.dsmmarketandroid.presentation.ui.myPost.CompleteRentDialog
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailActivity
import org.jetbrains.anko.startActivity

class MyRentListAdapter(
    private val context: Context,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<MyRentListAdapter.ViewHolder>() {

    private var listItems = arrayListOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMyPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setItems(items: List<ProductModel>) {
        listItems = items as ArrayList<ProductModel>
        notifyDataSetChanged()
    }

    fun deleteAt(position: Int) {
        listItems.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(private val binding: ItemMyPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = listItems[adapterPosition]
            binding.product = item
            binding.clParent.setOnClickListener { context.startActivity<RentDetailActivity>("post_id" to item.postId) }
            binding.ivEdit.setOnClickListener { context.startActivity<ModifyRentActivity>("post_id" to item.postId) }
            binding.ivComplete.setOnClickListener {
                val dialog = CompleteRentDialog()
                val args = Bundle()
                args.putInt("position", adapterPosition)
                dialog.arguments = args
                dialog.show(fragmentManager, "")
            }
        }
    }
}