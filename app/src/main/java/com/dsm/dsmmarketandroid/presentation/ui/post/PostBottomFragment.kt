package com.dsm.dsmmarketandroid.presentation.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_post_bottom.view.*
import org.jetbrains.anko.support.v4.startActivity

class PostBottomFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_post_bottom, container, false)

        rootView.cl_purchase.setOnClickListener {
            dismiss()
            startActivity<PostPurchaseActivity>()
        }

        rootView.cl_rent.setOnClickListener {
            dismiss()
            startActivity<PostRentActivity>()
        }

        return rootView
    }
}