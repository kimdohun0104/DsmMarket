package com.dsm.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistoryRoomEntity(
    @PrimaryKey
    val content: String
)