package com.dsm.data.dataSource.purchase

import com.dsm.data.local.db.entity.PurchaseRoomEntity
import com.dsm.data.remote.entity.PurchaseListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface PurchaseDataSource {
    fun getRemotePurchaseList(page: Int, pageSize: Int): Flowable<PurchaseListEntity>

    fun getLocalPurchaseList(page: Int, pageSize: Int): List<PurchaseRoomEntity>

    fun cachePurchase(purchaseRoomEntity: PurchaseRoomEntity): Completable
}