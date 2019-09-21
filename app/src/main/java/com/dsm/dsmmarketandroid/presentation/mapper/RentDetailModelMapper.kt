package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.RentDetail
import com.dsm.dsmmarketandroid.presentation.model.RentDetailModel

class RentDetailModelMapper : Mapper<RentDetail,RentDetailModel> {
    override fun mapFrom(from: RentDetail) = RentDetailModel(
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
        category = from.category
    )

}