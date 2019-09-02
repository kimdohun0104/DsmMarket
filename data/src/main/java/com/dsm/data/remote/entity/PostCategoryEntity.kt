package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PostCategoryEntity(
    @SerializedName("parent")
    val parent: String,

    @SerializedName("body")
    val body: List<String>
)