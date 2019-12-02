package com.dsm.data.dataSource.product

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.ProductDao
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.dao.RentDao
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.local.db.entity.RecentPurchaseRoomEntity
import com.dsm.data.local.db.entity.RecentRentRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class ProductDataSourceImpl(
    private val api: Api,
    private val productDao: ProductDao,
    private val purchaseDao: PurchaseDao,
    private val rentDao: RentDao
) : ProductDataSource {

    override fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getPurchaseList(page, pageSize, search, category).addSchedulers()

    override fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getRentList(page, pageSize, search, category).addSchedulers()

    override fun getLocalProductList(page: Int, pageSize: Int, type: Int): List<ProductRoomEntity> =
        productDao.getProduct(page, pageSize, type)

    override fun addLocalProductList(list: List<ProductRoomEntity>): Completable =
        productDao.addProductList(list)

    override fun getRemoteInterestProduct(type: Int): Flowable<ProductListEntity> =
        api.getInterest(type).addSchedulers()

    override fun getMyPurchase(): Flowable<ProductListEntity> =
        api.getMyPurchase().addSchedulers()

    override fun getMyRent(): Flowable<ProductListEntity> =
        api.getMyRent().addSchedulers()

    override fun completePurchase(postId: Int): Flowable<Unit> =
        api.completePurchase(postId).addSchedulers()

    override fun completeRent(postId: Int): Flowable<Unit> =
        api.completeRent(postId).addSchedulers()

    override fun getRecentPurchase(): Flowable<List<RecentPurchaseRoomEntity>> =
        purchaseDao.getRecentPurchase().addSchedulers()

    override fun getRecentRent(): Flowable<List<RecentRentRoomEntity>> =
        rentDao.getRecentRent().addSchedulers()
}