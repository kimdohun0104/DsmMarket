package com.dsm.dsmmarketandroid.presentation.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsm.dsmmarketandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PostBottomFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_post_bottom, container, false)
        return rootView
    }
}