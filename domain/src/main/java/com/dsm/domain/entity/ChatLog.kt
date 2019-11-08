package com.dsm.domain.entity

data class ChatLog(
    val me: Boolean,

    val message: String,

    val time: String,

    val date: String
)