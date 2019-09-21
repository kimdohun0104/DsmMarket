package com.dsm.data.dataSource.purchase

import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

interface PurchaseDataSource {
    fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getRemotePurchaseDetail(postId: Int): Flowable<Response<PurchaseDetailEntity>>

    fun getLocalPurchaseDetail(postId: Int): PurchaseDetailRoomEntity

    fun addLocalPurchaseDetail(postDetailRoomEntity: PurchaseDetailRoomEntity): Completable
}