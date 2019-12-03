package com.dsm.dsmmarketandroid.presentation.util

import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.dsm.dsmmarketandroid.presentation.ui.adapter.DetailImageListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ModifyImageListAdapter

object BindingUtil {

    @JvmStatic
    @BindingAdapter("spinner")
    fun bindSpn(view: Spinner, value: MutableLiveData<String>) {
        value.value = view.selectedItem.toString()
    }

    @JvmStatic
    @BindingConversion
    fun convertColorToDrawable(color: Int): ColorDrawable? {
        return if (color != 0) ColorDrawable(color) else null
    }

    @JvmStatic
    @BindingAdapter("hour")
    fun bindHour(view: NumberPicker, value: MutableLiveData<String>) {
        value.value = view.value.toString()
    }

    @JvmStatic
    @BindingAdapter("minute")
    fun bindMinute(view: NumberPicker, value: MutableLiveData<String>) {
        if (view.value == 0) {
            value.value = "00"
        } else {
            value.value = "30"
        }
    }

    @JvmStatic
    @BindingAdapter("detailImages")
    fun bindDetailImages(view: RecyclerView, value: List<String>?) {
        val adapter = view.adapter as DetailImageListAdapter
        value?.let { adapter.listItems = it }
    }

    @JvmStatic
    @BindingAdapter("image")
    fun bindImage(view: ImageView, value: String?) {
        value?.let { GlideApp.with(view).load(it.trim()).into(view) }
    }

    @JvmStatic
    @BindingAdapter("modifyImages")
    fun bindModifyImages(view: RecyclerView, value: ListLiveData<String>?) {
        val adapter = view.adapter as ModifyImageListAdapter
        value?.let { adapter.setItems(it.value!!) }
    }

    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun bindIsRefreshing(view: SwipeRefreshLayout, value: LiveData<Boolean>) {
        value.value?.let { view.isRefreshing = it }
    }

    @JvmStatic
    @BindingAdapter("pagerIndex")
    fun bindPagerIndex(view: ViewPager2, value: MutableLiveData<Int>) {
        value.value = view.currentItem
    }

    @JvmStatic
    @BindingAdapter("textWithId")
    fun bindTextWithId(view: Button, value: LiveData<Int>) {
        value.value?.let { view.text = view.resources.getString(it) }
    }
}