package com.dsm.dsmmarketandroid.presentation.ui.myPost.rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostViewModel
import kotlinx.android.synthetic.main.dialog_complete_rent.*

class CompleteRentDialog : DialogFragment() {

    private val viewModel: MyPostViewModel by lazy { ViewModelProviders.of(activity!!)[MyPostViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_complete_rent, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_complete.setOnClickListener { viewModel.completeRent(arguments?.getInt("position") ?: -1) }

        btn_cancel.setOnClickListener { dismiss() }

        viewModel.dismissEvent.observe(this, Observer { dismiss() })
    }
}