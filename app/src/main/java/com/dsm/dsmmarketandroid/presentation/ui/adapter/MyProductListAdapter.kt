package com.dsm.dsmmarketandroid.presentation.ui.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dsmmarketandroid.databinding.ItemMyPostBinding
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.myPost.CompleteDialog
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.modifyPurchase.ModifyPurchaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail.PurchaseDetailActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent.ModifyRentActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail.RentDetailActivity
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import org.jetbrains.anko.startActivity

class MyProductListAdapter(
    private val type: Int,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<MyProductListAdapter.ViewHolder>() {

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
            val context = binding.root.context
            binding.product = item
            binding.clParent.setOnClickListener {
                val intent =
                    if (type == ProductType.PURCHASE)
                        Intent(context, PurchaseDetailActivity::class.java)
                    else
                        Intent(context, RentDetailActivity::class.java)
                intent.putExtra("post_id", item.postId)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    context as Activity,
                    Pair.create(binding.ivProduct as View, "image"),
                    Pair.create(binding.tvTitle as View, "title")
                )
                context.startActivity(intent, options.toBundle())
            }
            binding.ivEdit.setOnClickListener {
                if (type == ProductType.PURCHASE)
                    context.startActivity<ModifyPurchaseActivity>("post_id" to item.postId)
                else
                    context.startActivity<ModifyRentActivity>("post_id" to item.postId)
            }


            binding.ivComplete.setOnClickListener {
                CompleteDialog().apply {
                    arguments = Bundle().apply {
                        putInt("position", adapterPosition)
                        putInt("type", type)
                    }
                    show(this@MyProductListAdapter.fragmentManager, "")
                }
            }
        }
    }
}