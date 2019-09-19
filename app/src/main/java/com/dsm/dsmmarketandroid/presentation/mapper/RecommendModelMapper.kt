package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Recommend
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel

class RecommendModelMapper : Mapper<List<Recommend>, List<RecommendModel>> {
    override fun mapFrom(from: List<Recommend>): List<RecommendModel> =
        from.map {
            RecommendModel(
                postId = it.postId,
                title = it.title,
                img = it.img
            )
        }

}