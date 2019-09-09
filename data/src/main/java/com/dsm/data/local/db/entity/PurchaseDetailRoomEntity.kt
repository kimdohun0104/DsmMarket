package com.dsm.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PurchaseDetailRoomEntity(
    val img: List<String>,

    @PrimaryKey
    val id: Int,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    val commentCount: Int
)