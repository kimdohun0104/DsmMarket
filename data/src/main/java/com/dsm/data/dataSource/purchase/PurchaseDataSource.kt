package com.dsm.data.dataSource.purchase

import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface PurchaseDataSource {

    fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getLocalPurchaseList(page: Int, pageSize: Int): List<ProductRoomEntity>

    fun addLocalPurchaseList(list: List<ProductRoomEntity>) : Completable
}
