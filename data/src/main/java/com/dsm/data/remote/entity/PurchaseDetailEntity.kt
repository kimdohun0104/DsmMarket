package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PurchaseDetailEntity(
    val img: List<String>,

    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    @SerializedName("comments")
    val commentCount: Int,

    val author: String
)