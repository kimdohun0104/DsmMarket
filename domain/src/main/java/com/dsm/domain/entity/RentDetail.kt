package com.dsm.domain.entity

data class RentDetail(
    val img: String,

    val author: String,

    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    val possibleTime: String,

    val commentCount: Int
)