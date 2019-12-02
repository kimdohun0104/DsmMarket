package com.dsm.data.dataSource.product

import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.local.db.entity.RecentPurchaseRoomEntity
import com.dsm.data.local.db.entity.RecentRentRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProductDataSource {

    fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getLocalProductList(page: Int, pageSize: Int, type: Int): List<ProductRoomEntity>

    fun addLocalProductList(list: List<ProductRoomEntity>) : Completable

    fun getRemoteInterestProduct(type: Int): Flowable<ProductListEntity>

    fun getMyPurchase(): Flowable<ProductListEntity>

    fun getMyRent(): Flowable<ProductListEntity>

    fun completePurchase(postId: Int): Flowable<Unit>

    fun completeRent(postId: Int): Flowable<Unit>

    fun getRecentPurchase(): Flowable<List<RecentPurchaseRoomEntity>>

    fun getRecentRent(): Flowable<List<RecentRentRoomEntity>>
}