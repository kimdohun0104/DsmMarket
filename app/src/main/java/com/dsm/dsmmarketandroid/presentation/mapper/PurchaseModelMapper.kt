package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Purchase
import com.dsm.dsmmarketandroid.presentation.model.PurchaseModel

class PurchaseModelMapper : Mapper<List<Purchase>, List<PurchaseModel>> {
    override fun mapFrom(from: List<Purchase>): List<PurchaseModel> {
        val list = arrayListOf<PurchaseModel>()
        from.forEach {
            list.add(PurchaseModel(
                postId = it.postId,
                title = it.title,
                price = it.price,
                img = it.img,
                createdAt = it.createdAt
            ))
        }
        return list
    }

}