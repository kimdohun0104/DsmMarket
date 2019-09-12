package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RentDetailEntity(
    val img: String,

    val author: String,

    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    @SerializedName("possible_time")
    val possibleTime: String,

    @SerializedName("comments")
    val commentCount: Int,

    @SerializedName("interest")
    val isInterest: Boolean
)