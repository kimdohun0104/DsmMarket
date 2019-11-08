package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ChatLogListEntity(
    @SerializedName("list")
    val chatLogList: List<ChatLogEntity>
)