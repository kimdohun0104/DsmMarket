package com.dsm.data.mapper

import com.dsm.data.local.db.entity.PurchaseRoomEntity
import com.dsm.data.remote.entity.PurchaseListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Purchase

class PurchaseMapper : Mapper<PurchaseListEntity, List<Purchase>> {
    override fun mapFrom(from: PurchaseListEntity): List<Purchase> {
        val list = arrayListOf<Purchase>()
        from.purchaseList.forEach {
            list.add(Purchase(
                postId = it.postId,
                createdAt = it.createdAt,
                img = it.img,
                price = it.price,
                title = it.title
            ))
        }
        return list
    }

    fun mapFrom(from: Purchase) = PurchaseRoomEntity(
        postId = from.postId,
        title = from.title,
        price = from.price,
        img = from.img,
        createdAt = from.createdAt
    )

    fun mapFrom(from: List<PurchaseRoomEntity>): List<Purchase> {
        val list = arrayListOf<Purchase>()
        from.forEach {
            list.add(Purchase(
                postId = it.postId,
                createdAt = it.createdAt,
                img = it.img,
                price = it.price,
                title = it.title
            ))
        }
        return list
    }
}