package com.dsm.data.dataSource.interest

import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable
import retrofit2.Response

interface InterestDataSource {
    fun interest(postId: Int, type: Int): Flowable<Response<Unit>>

    fun unInterest(postId: Int, type: Int): Flowable<Response<Unit>>

    fun getInterest(type: Int): Flowable<ProductListEntity>
}