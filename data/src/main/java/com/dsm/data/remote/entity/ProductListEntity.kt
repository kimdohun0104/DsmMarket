package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ProductListEntity(
    @SerializedName("list")
    val productList: List<ProductEntity>
)