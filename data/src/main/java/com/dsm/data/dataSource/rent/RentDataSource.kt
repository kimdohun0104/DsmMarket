package com.dsm.data.dataSource.rent

import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

interface RentDataSource {
    fun getRemoteRentList(page: Int, pageSize: Int): Flowable<ProductListEntity>
}