package com.dsm.dsmmarketandroid.presentation.util

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.DetailImagePagerAdapter
import com.dsm.dsmmarketandroid.presentation.ui.adapter.ModifyImageListAdapter


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

    @JvmStatic
    @BindingAdapter("bind:hour")
    fun bindHour(view: NumberPicker, value: MutableLiveData<String>) {
        value.value = view.value.toString()
    }

    @JvmStatic
    @BindingAdapter("bind:minute")
    fun bindMinute(view: NumberPicker, value: MutableLiveData<String>) {
        if (view.value == 0) {
            value.value = "00"
        } else {
            value.value = "30"
        }
    }

    @JvmStatic
    @BindingAdapter("bind:detailImages")
    fun bindDetailImages(view: ViewPager2, value: List<String>?) {
        val adapter = view.adapter as DetailImagePagerAdapter
        value?.let { adapter.imageList = it }
    }

    @JvmStatic
    @BindingAdapter("bind:image")
    fun bindImage(view: ImageView, value: String?) {
//        value?.let { Picasso.get().load(it).into(view) }
        value?.let { Glide.with(view).load(it).placeholder(R.drawable.image_placeholder).error(R.drawable.image_error).into(view) }
    }

    @JvmStatic
    @BindingAdapter("bind:modifyImages")
    fun bindModifyImages(view: RecyclerView, value: ListLiveData<String>?) {
        val adapter = view.adapter as ModifyImageListAdapter
        value?.let { adapter.setItems(it.value!!) }
    }
}