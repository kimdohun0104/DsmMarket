package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RentImageEntity(
    @SerializedName("img")
    val image: String
)