package com.dsm.dsmmarketandroid.presentation.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.dsm.dsmmarketandroid.R

class LoadingDialog : DialogFragment() {

    companion object {
        private val dialog = LoadingDialog()

        init {
            dialog.isCancelable = false
        }

        fun show(fragmentManager: FragmentManager) = dialog.show(fragmentManager, "")

        fun hide() = dialog.dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.dialog_loading, container, false)
        return rootView
    }

}