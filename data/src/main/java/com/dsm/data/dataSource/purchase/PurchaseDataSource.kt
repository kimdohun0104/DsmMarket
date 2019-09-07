package com.dsm.data.dataSource.purchase

import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

interface PurchaseDataSource {
    fun getRemotePurchaseList(page: Int, pageSize: Int): Flowable<ProductListEntity>

}