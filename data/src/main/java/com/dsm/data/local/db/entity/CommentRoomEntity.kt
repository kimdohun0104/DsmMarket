package com.dsm.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentRoomEntity(

    @PrimaryKey
    val postId: Int,

    val type: Int,

    val nick: String,

    val content: String,

    val createdAt: String
)