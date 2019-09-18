package com.dsm.data.repository

import com.dsm.data.dataSource.recommend.RecommendDataSource
import com.dsm.data.mapper.RecommendMapper
import com.dsm.domain.entity.Recommend
import com.dsm.domain.repository.RecommendRepository
import io.reactivex.Flowable

class RecommendRepositoryImpl(private val recommendDataSource: RecommendDataSource) : RecommendRepository {

    private val recommendMapper = RecommendMapper()

    override fun getRecommendProduct(category: String): Flowable<List<Recommend>> =
        recommendDataSource.getRecommendProduct(category).map(recommendMapper::mapFrom)

    override fun getRelatedProduct(category: String, type: Int): Flowable<List<Recommend>> =
        recommendDataSource.getRelatedProduct(category, type).map(recommendMapper::mapFrom)

}