package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class LoginTokenEntity(
    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("access_token")
    val accessToken: String
)