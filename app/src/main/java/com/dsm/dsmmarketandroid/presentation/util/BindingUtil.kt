package com.dsm.dsmmarketandroid.presentation.util

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingConversion



object BindingUtil {

    @JvmStatic
    @BindingAdapter("bind:spinner")
    fun bindSpn(view: Spinner, value: MutableLiveData<String>) {
        value.value = view.selectedItem.toString()
    }

    @JvmStatic
    @BindingConversion
    fun convertColorToDrawable(color: Int): ColorDrawable? {
        return if (color != 0) ColorDrawable(color) else null
    }
}