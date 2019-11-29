package com.dsm.data.dataSource.purchase

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.InterestDao
import com.dsm.data.local.db.dao.ProductDao
import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class PurchaseDataSourceImpl(
    private val api: Api,
    private val productDao: ProductDao,
    private val interestDao: InterestDao
) : PurchaseDataSource {

    override fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getPurchaseList(page, pageSize, search, category).addSchedulers()

    override fun getLocalPurchaseList(page: Int, pageSize: Int): List<ProductRoomEntity> =
        productDao.getProduct(page, pageSize, 0)

    override fun addLocalPurchaseList(list: List<ProductRoomEntity>): Completable =
        productDao.addProductList(list)

    override fun getRemoteInterestPurchase(): Flowable<ProductListEntity> =
        api.getInterest(0).addSchedulers()

    override fun getLocalInterestPurchase(): List<InterestProductRoomEntity> =
        interestDao.getInterestPurchase()

    override fun addLocalInterestPurchase(interestPurchase: List<InterestProductRoomEntity>): Completable =
        interestDao.addInterestPurchase(interestPurchase)

}