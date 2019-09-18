package com.dsm.data.dataSource.recommend

import com.dsm.data.remote.entity.RecommendListEntity
import io.reactivex.Flowable

interface RecommendDataSource {

    fun getRecommendProduct(category: String): Flowable<RecommendListEntity>

    fun getRelatedProduct(category: String, type: Int): Flowable<RecommendListEntity>
}