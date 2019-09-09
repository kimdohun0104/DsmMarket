package com.dsm.data.mapper

import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.PurchaseDetail

class PurchaseDetailMapper : Mapper<PurchaseDetailEntity, PurchaseDetail> {
    override fun mapFrom(from: PurchaseDetailEntity) = PurchaseDetail(
        img = from.img,
        id = from.id,
        createdAt = from.createdAt,
        price = from.price,
        title = from.title,
        commentCount = from.commentCount,
        content = from.content
    )

    fun mapFrom(from: PurchaseDetail) = PurchaseDetailRoomEntity(
        img = from.img,
        id = from.id,
        createdAt = from.createdAt,
        price = from.price,
        title = from.title,
        commentCount = from.commentCount,
        content = from.content
    )

    fun mapFrom(from: PurchaseDetailRoomEntity) = PurchaseDetail(
        img = from.img,
        id = from.id,
        createdAt = from.createdAt,
        price = from.price,
        title = from.title,
        commentCount = from.commentCount,
        content = from.content
    )
}