package com.dsm.data.mapper

import com.dsm.data.local.db.entity.RentDetailRoomEntity
import com.dsm.data.remote.entity.RentDetailEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.RentDetail

class RentDetailMapper : Mapper<RentDetailEntity, RentDetail> {
    override fun mapFrom(from: RentDetailEntity) = RentDetail(
        img = from.img,
        author = from.author,
        id = from.id,
        title = from.title,
        content = from.content,
        createdAt = from.createdAt,
        price = from.price,
        possibleTime = from.possibleTime,
        commentCount = from.commentCount,
        isInterest = from.isInterest,
        category = from.category,
        isMe = from.isMe
    )

    fun mapFrom(from: RentDetail) = RentDetailRoomEntity(
        img = from.img,
        author = from.author,
        id = from.id,
        title = from.title,
        content = from.content,
        createdAt = from.createdAt,
        price = from.price,
        possibleTime = from.possibleTime,
        commentCount = from.commentCount,
        isInterest = from.isInterest,
        isMe = from.isMe
    )

    fun mapFrom(from: RentDetailRoomEntity) = RentDetail(
        img = from.img,
        author = from.author,
        id = from.id,
        title = from.title,
        content = from.content,
        createdAt = from.createdAt,
        price = from.price,
        possibleTime = from.possibleTime,
        commentCount = from.commentCount,
        isInterest = from.isInterest,
        category = "",
        isMe = from.isMe
    )
}