package com.dsm.dsmmarketandroid.presentation.util

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

object BindingUtil {

    @JvmStatic
    @BindingAdapter("bind:spinner")
    fun bindSpn(view: Spinner, value: MutableLiveData<String>) {
        value.value = view.selectedItem.toString()
    }
}