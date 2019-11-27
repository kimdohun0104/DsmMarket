package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class TokenEntity(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String,

    val nick: String
)