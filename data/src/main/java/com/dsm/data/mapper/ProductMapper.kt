package com.dsm.data.mapper

import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Product

class ProductMapper : Mapper<ProductListEntity, List<Product>> {
    override fun mapFrom(from: ProductListEntity): List<Product> =
        from.productList.map {
            Product(
                postId = it.postId,
                createdAt = it.createdAt,
                price = it.price,
                title = it.title,
                img = it.img
            )
        }

    fun mapFrom(from: ProductRoomEntity) = Product(
        postId = from.postId,
        createdAt = from.createdAt,
        img = from.img,
        price = from.price,
        title = from.title
    )

    fun mapFrom(from: List<Product>) : List<ProductRoomEntity> =
        from.map {
            ProductRoomEntity(
                postId = it.postId,
                createdAt = it.createdAt,
                price = it.price,
                title = it.title,
                img = it.img
            )
        }
}
