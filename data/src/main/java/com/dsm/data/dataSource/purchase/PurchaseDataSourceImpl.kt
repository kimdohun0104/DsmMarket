package com.dsm.data.dataSource.purchase

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

class PurchaseDataSourceImpl(
    private val api: Api,
    private val purchaseDao: PurchaseDao
) : PurchaseDataSource {

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
}