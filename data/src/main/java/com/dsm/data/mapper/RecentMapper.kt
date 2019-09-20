package com.dsm.data.mapper

import com.dsm.data.local.db.entity.RecentPurchaseRoomEntity
import com.dsm.data.local.db.entity.RecentRentRoomEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Product

class RecentMapper : Mapper<List<RecentPurchaseRoomEntity>, List<Product>> {

    override fun mapFrom(from: List<RecentPurchaseRoomEntity>): List<Product> =
        from.map {
            Product(
                postId = it.id,
                createdAt = it.createdAt,
                price = it.price,
                title = it.title,
                img = it.img[0]
            )
        }


    fun mapFromRent(from: List<RecentRentRoomEntity>) : List<Product> =
        from.map {
            Product(
                postId = it.id,
                createdAt = it.createdAt,
                price = it.price,
                title = it.title,
                img = it.img
            )
        }
}