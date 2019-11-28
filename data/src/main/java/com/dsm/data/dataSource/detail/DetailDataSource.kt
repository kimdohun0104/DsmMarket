package com.dsm.data.dataSource.detail

import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailDataSource {

    fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetailEntity>

    fun getLocalPurchaseDetail(postId: Int): PurchaseDetailRoomEntity

    fun addLocalPurchaseDetail(postDetailRoomEntity: PurchaseDetailRoomEntity): Completable
}