package com.dsm.data.mapper

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
}