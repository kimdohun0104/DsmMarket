package com.dsm.data.mapper

import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Product

class InterestProductMapper : Mapper<ProductListEntity, List<Product>> {
    override fun mapFrom(from: ProductListEntity): List<Product> =
        from.productList.map {
            Product(
                postId = it.postId,
                title = it.title,
                img = it.img,
                createdAt = it.createdAt,
                price = it.price
            )
        }

    fun mapFrom(from: InterestProductRoomEntity): Product =
        Product(
            postId = from.postId,
            price = from.price,
            createdAt = from.createdAt,
            title = from.title,
            img = from.img
        )

    fun mapFrom(from: List<Product>, type: Int): List<InterestProductRoomEntity> =
        from.map {
            InterestProductRoomEntity(
                postId = it.postId,
                img = it.img,
                title = it.title,
                type = type,
                createdAt = it.createdAt,
                price = it.price
            )
        }
}