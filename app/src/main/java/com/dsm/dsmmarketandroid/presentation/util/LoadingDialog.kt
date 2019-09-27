package com.dsm.dsmmarketandroid.presentation.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.dsm.dsmmarketandroid.R

class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
    }
}