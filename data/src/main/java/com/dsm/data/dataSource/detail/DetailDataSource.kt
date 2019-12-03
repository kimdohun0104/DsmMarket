package com.dsm.data.dataSource.detail

import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.local.db.entity.RentDetailRoomEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import com.dsm.data.remote.entity.RecommendListEntity
import com.dsm.data.remote.entity.RentDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailDataSource {

    fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetailEntity>

    fun getLocalPurchaseDetail(postId: Int): PurchaseDetailRoomEntity?

    fun addLocalPurchaseDetail(purchaseDetailRoomEntity: PurchaseDetailRoomEntity): Completable

    fun getRemoteRentDetail(postId: Int): Flowable<RentDetailEntity>

    fun getLocalRentDetail(postId: Int): RentDetailRoomEntity?

    fun addLocalRentDetail(rentDetailRoomEntity: RentDetailRoomEntity): Completable

    fun interest(postId: Int, type: Int): Flowable<Unit>

    fun unInterest(postId: Int, type: Int): Flowable<Unit>

    fun getRecommendProduct(): Flowable<RecommendListEntity>

    fun getRelatedProduct(postId: Int, type: Int): Flowable<RecommendListEntity>

    fun modifyPurchase(param: Any): Flowable<Unit>

    fun modifyRent(param: Any): Flowable<Unit>
}