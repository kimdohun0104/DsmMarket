package com.dsm.dsmmarketandroid.presentation.model

data class PurchaseDetailModel(
    val img: List<String>,

    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    val commentCount: Int,

    val author: String,

    val isInterest: Boolean,

    val category: String,

    val isMe: Boolean
)