package com.dsm.domain.entity

data class PurchaseDetail(
    val img: List<String>,

    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    val commentCount: Int,

    val author: String,

    val isInterest: Boolean
)