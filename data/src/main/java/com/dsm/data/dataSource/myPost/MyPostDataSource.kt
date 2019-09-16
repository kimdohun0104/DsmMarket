package com.dsm.data.dataSource.myPost

import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

interface MyPostDataSource {
    fun getMyPurchase(): Flowable<ProductListEntity>

    fun getMyRent(): Flowable<ProductListEntity>
}