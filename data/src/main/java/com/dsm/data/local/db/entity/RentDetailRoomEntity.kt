package com.dsm.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RentDetailRoomEntity(
    @PrimaryKey
    val id: Int,

    val img: String,

    val author: String,

    val title: String,

    val content: String,

    val createdAt: String,

    val price: String,

    val possibleTime: String,

    val commentCount: Int
)