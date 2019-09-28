package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PurchaseImageEntity(
    @SerializedName("list")
    val images: List<String>
)