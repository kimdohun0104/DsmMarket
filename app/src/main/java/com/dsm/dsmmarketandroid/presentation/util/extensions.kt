package com.dsm.dsmmarketandroid.presentation.util

import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayout

fun EditText.setEditorActionListener(action: Int, callback: () -> Unit) =
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == action) {
            callback()
            return@setOnEditorActionListener true
        }
        false
    }

fun TabLayout.addOnTabSelectedListener(callback: (tab: TabLayout.Tab) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            callback(tab!!)
        }
    })
}

fun Spinner.onItemSelectedListener(callback: (position: Int) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            callback(position)
        }
    }
}

fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()