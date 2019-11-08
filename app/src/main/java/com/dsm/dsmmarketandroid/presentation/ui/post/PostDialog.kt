package com.dsm.dsmmarketandroid.presentation.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentActivity
import kotlinx.android.synthetic.main.dialog_post.*
import org.jetbrains.anko.support.v4.startActivity

class PostDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_post, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cl_post_purchase.setOnClickListener {
            dismiss()
            startActivity<PostPurchaseActivity>()
        }

        cl_post_rent.setOnClickListener {
            dismiss()
            startActivity<PostRentActivity>()
        }
    }
}