package com.dsm.data.dataSource.detail

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.PurchaseDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailDataSourceImpl(
    private val api: Api,
    private val purchaseDao: PurchaseDao
) : DetailDataSource {

    override fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetailEntity> =
        api.getPurchaseDetail(postId).addSchedulers()

    override fun getLocalPurchaseDetail(postId: Int): PurchaseDetailRoomEntity =
        purchaseDao.getPurchaseDetail(postId)

    override fun addLocalPurchaseDetail(postDetailRoomEntity: PurchaseDetailRoomEntity): Completable =
        purchaseDao.addPurchaseDetail(postDetailRoomEntity)

}