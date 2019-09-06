package com.dsm.data.mapper

import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Product

class ProductMapper : Mapper<ProductListEntity, List<Product>> {
    override fun mapFrom(from: ProductListEntity): List<Product> {
        val list = arrayListOf<Product>()
        from.productList.forEach {
            list.add(Product(
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