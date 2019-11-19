package com.dsm.dsmmarketandroid.presentation.model

data class RentDetailModel(
    val img: String,

    val author: String,

    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    val possibleTime: String,

    val commentCount: Int,

    val isInterest: Boolean,

    val category: String,

    val isMe: Boolean
)