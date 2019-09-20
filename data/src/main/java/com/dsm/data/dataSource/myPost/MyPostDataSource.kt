package com.dsm.data.dataSource.myPost

import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable
import retrofit2.Response

interface MyPostDataSource {
    fun getMyPurchase(): Flowable<ProductListEntity>

    fun getMyRent(): Flowable<ProductListEntity>

    fun completePurchase(postId: Int): Flowable<Response<Unit>>

    fun completeRent(postId: Int): Flowable<Response<Unit>>
}