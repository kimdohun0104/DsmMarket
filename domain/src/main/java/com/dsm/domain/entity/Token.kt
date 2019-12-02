package com.dsm.domain.entity

data class Token(
    val accessToken: String,

    val refreshToken: String,

    val nick: String
)