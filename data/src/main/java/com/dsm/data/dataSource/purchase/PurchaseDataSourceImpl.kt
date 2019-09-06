package com.dsm.data.dataSource.purchase

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.entity.PurchaseRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.PurchaseListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class PurchaseDataSourceImpl(
    private val api: Api,
    private val purchaseDao: PurchaseDao
) : PurchaseDataSource {

    override fun getRemotePurchaseList(page: Int, pageSize: Int): Flowable<PurchaseListEntity> =
        api.getPurchaseList(page, pageSize).addSchedulers()

    override fun getLocalPurchaseList(page: Int, pageSize: Int): List<PurchaseRoomEntity> =
        purchaseDao.getPurchaseList(page, pageSize)

    override fun cachePurchase(purchaseRoomEntity: PurchaseRoomEntity): Completable =
        purchaseDao.cachePurchase(purchaseRoomEntity).addSchedulers()
}