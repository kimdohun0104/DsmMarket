package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Product
import com.dsm.dsmmarketandroid.presentation.model.ProductModel

class PurchaseModelMapper : Mapper<List<Product>, List<ProductModel>> {
    override fun mapFrom(from: List<Product>): List<ProductModel> {
        val list = arrayListOf<ProductModel>()
        from.forEach {
            list.add(ProductModel(
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