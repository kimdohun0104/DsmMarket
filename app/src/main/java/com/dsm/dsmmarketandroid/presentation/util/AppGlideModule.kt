package com.dsm.dsmmarketandroid.presentation.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.dsm.dsmmarketandroid.R

@GlideModule
class AppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val requestOptions = RequestOptions().apply {
            placeholder(R.drawable.image_placeholder)
            error(R.drawable.image_error)
        }
        builder.setDefaultRequestOptions(requestOptions)
    }
}