package com.dsm.data.dataSource.recommend

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.RecommendListEntity
import io.reactivex.Flowable

class RecommendDataSourceImpl(private val api: Api) : RecommendDataSource {

    override fun getRecommendProduct(category: String): Flowable<RecommendListEntity> =
        api.getRecommendProduct(category).addSchedulers()

    override fun getRelatedProduct(category: String, type: Int): Flowable<RecommendListEntity> =
        api.getRelatedProduct(category, type).addSchedulers()

}