package com.dsm.data.dataSource.purchaseBefore

import com.dsm.data.remote.entity.PurchaseImageEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

interface PurchaseDataSourceBefore {
    fun modifyPurchase(params: Any): Flowable<Response<Unit>>

    fun addSearchHistory(search: String): Completable

    fun getPurchaseImage(postId: Int): Flowable<PurchaseImageEntity>
}