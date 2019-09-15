package com.dsm.data.local.db.entity

data class RecentPurchaseRoomEntity(
    val id: Int,

    val title: String,

    val createdAt: String,

    val price: String,

    val img: List<String>
)