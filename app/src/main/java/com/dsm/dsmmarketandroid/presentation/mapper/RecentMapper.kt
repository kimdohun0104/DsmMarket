package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Recent
import com.dsm.dsmmarketandroid.presentation.model.ProductModel

class RecentMapper : Mapper<List<Recent>, List<ProductModel>> {
    override fun mapFrom(from: List<Recent>): List<ProductModel> =
        from.map {
            ProductModel(
                postId = it.id,
                img = it.img,
                title = it.title,
                price = it.price,
                createdAt = it.createdAt
            )
        }

}