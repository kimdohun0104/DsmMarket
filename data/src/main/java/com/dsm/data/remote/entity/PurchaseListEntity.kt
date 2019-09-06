package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class PurchaseListEntity(
    @SerializedName("list")
    val purchaseList: List<PurchaseEntity>
)