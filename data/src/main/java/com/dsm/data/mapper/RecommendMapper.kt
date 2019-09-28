package com.dsm.data.mapper

import com.dsm.data.remote.entity.RecommendListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Recommend

class RecommendMapper : Mapper<RecommendListEntity, List<Recommend>> {
    override fun mapFrom(from: RecommendListEntity): List<Recommend> =
        from.list.map {
            Recommend(
                postId = it.postId,
                title = it.title,
                img = it.img
            )
        }

}