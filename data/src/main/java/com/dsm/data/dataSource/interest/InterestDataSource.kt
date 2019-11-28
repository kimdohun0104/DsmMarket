package com.dsm.data.dataSource.interest

import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

interface InterestDataSource {

    fun getInterest(type: Int): Flowable<ProductListEntity>
}