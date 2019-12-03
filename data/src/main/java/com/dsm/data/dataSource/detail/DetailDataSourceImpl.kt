package com.dsm.data.dataSource.detail

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.dao.RentDao
import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.local.db.entity.RentDetailRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.PurchaseDetailEntity
import com.dsm.data.remote.entity.RecommendListEntity
import com.dsm.data.remote.entity.RentDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailDataSourceImpl(
    private val api: Api,
    private val purchaseDao: PurchaseDao,
    private val rentDao: RentDao
) : DetailDataSource {

    override fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetailEntity> =
        api.getPurchaseDetail(postId).addSchedulers()

    override fun getLocalPurchaseDetail(postId: Int): PurchaseDetailRoomEntity? =
        purchaseDao.getPurchaseDetail(postId)

    override fun addLocalPurchaseDetail(purchaseDetailRoomEntity: PurchaseDetailRoomEntity): Completable =
        purchaseDao.addPurchaseDetail(purchaseDetailRoomEntity)

    override fun getRemoteRentDetail(postId: Int): Flowable<RentDetailEntity> =
        api.getRentDetail(postId).addSchedulers()

    override fun getLocalRentDetail(postId: Int): RentDetailRoomEntity? =
        rentDao.getRentDetail(postId)

    override fun addLocalRentDetail(rentDetailRoomEntity: RentDetailRoomEntity): Completable =
        rentDao.addRentDetail(rentDetailRoomEntity)

    override fun interest(postId: Int, type: Int): Flowable<Unit> =
        api.interest(postId, type).addSchedulers()

    override fun unInterest(postId: Int, type: Int): Flowable<Unit> =
        api.unInterest(postId, type).addSchedulers()

    override fun getRecommendProduct(): Flowable<RecommendListEntity> =
        api.getRecommendProduct().addSchedulers()

    override fun getRelatedProduct(postId: Int, type: Int): Flowable<RecommendListEntity> =
        api.getRelatedProduct(postId, type).addSchedulers()

    override fun modifyPurchase(param: Any): Flowable<Unit> =
        api.modifyPurchase(param).addSchedulers()

    override fun modifyRent(param: Any): Flowable<Unit> =
        api.modifyRent(param).addSchedulers()
}