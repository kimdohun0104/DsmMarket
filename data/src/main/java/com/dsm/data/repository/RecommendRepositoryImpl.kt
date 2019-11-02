package com.dsm.data.repository

import com.dsm.data.dataSource.recommend.RecommendDataSource
import com.dsm.data.mapper.RecommendMapper
import com.dsm.domain.entity.Recommend
import com.dsm.domain.repository.RecommendRepository
import io.reactivex.Flowable

class RecommendRepositoryImpl(private val recommendDataSource: RecommendDataSource) : RecommendRepository {

    private val recommendMapper = RecommendMapper()

    override fun getRecommendProduct(): Flowable<List<Recommend>> =
        recommendDataSource.getRecommendProduct().map(recommendMapper::mapFrom)

    override fun getRelatedProduct(postId: Int, type: Int): Flowable<List<Recommend>> =
        recommendDataSource.getRelatedProduct(postId, type).map(recommendMapper::mapFrom)

}