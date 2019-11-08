package com.dsm.data.remote.entity

data class ChatLogEntity(
    val me: Boolean,

    val message: String,

    val createdAt: String
)