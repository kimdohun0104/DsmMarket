package com.dsm.data.dataSource.purchaseBefore

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.dao.SearchDao
import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import com.dsm.data.remote.entity.PurchaseImageEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

class PurchaseDataSourceImplBefore(
    private val api: Api,
    private val purchaseDao: PurchaseDao,
    private val searchDao: SearchDao
) : PurchaseDataSourceBefore {

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getPurchaseList(page, pageSize, search, category).addSchedulers()

    override fun getRemotePurchaseDetail(postId: Int): Flowable<Response<PurchaseDetailEntity>> =
        api.getPurchaseDetail(postId, 0).addSchedulers()

    override fun getLocalPurchaseDetail(postId: Int): PurchaseDetailRoomEntity =
        purchaseDao.getPurchaseDetail(postId)

    override fun addLocalPurchaseDetail(postDetailRoomEntity: PurchaseDetailRoomEntity): Completable =
        purchaseDao.addPurchaseDetail(postDetailRoomEntity).addSchedulers()

    override fun modifyPurchase(params: Any): Flowable<Response<Unit>> =
        api.modifyPurchase(params).addSchedulers()

    override fun addSearchHistory(search: String): Completable =
        searchDao.addSearchHistory(SearchHistoryRoomEntity(search)).addSchedulers()

    override fun getPurchaseImage(postId: Int): Flowable<PurchaseImageEntity> =
        api.getPurchaseImage(postId).addSchedulers()
}