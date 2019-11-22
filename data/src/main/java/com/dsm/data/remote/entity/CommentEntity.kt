package com.dsm.data.remote.entity

data class CommentEntity(
    val nick: String,

    val content: String,

    val createdAt: String,

    val isMe: Boolean
)