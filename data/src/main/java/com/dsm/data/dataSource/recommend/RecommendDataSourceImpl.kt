package com.dsm.data.dataSource.recommend

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.RecommendListEntity
import io.reactivex.Flowable

class RecommendDataSourceImpl(private val api: Api) : RecommendDataSource {

    override fun getRecommendProduct(postId: Int): Flowable<RecommendListEntity> =
        api.getRecommendProduct(postId).addSchedulers()

    override fun getRelatedProduct(postId: Int, type: Int): Flowable<RecommendListEntity> =
        api.getRelatedProduct(postId, type).addSchedulers()

}