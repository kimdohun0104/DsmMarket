package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CommentListEntity(
    @SerializedName("list")
    val commentLis: List<CommentEntity>
)