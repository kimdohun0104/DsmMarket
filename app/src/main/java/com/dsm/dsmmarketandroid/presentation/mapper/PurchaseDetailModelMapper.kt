package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.dsmmarketandroid.presentation.model.PurchaseDetailModel

class PurchaseDetailModelMapper : Mapper<PurchaseDetail, PurchaseDetailModel> {
    override fun mapFrom(from: PurchaseDetail) = PurchaseDetailModel(
        img = from.img,
        content = from.content,
        commentCount = from.commentCount,
        title = from.title,
        price = from.price,
        createdAt = from.createdAt.split("T")[0],
        id = from.id,
        author = from.author,
        isInterest = from.isInterest,
        category = from.category,
        isMe = from.isMe
    )

}