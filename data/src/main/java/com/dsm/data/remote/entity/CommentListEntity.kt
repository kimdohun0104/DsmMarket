package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CommentListEntity(
    @SerializedName("array")
    val commentLis: List<CommentEntity>
)