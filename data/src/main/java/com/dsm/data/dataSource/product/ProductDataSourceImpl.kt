package com.dsm.data.dataSource.product

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.InterestDao
import com.dsm.data.local.db.dao.ProductDao
import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class ProductDataSourceImpl(
    private val api: Api,
    private val productDao: ProductDao,
    private val interestDao: InterestDao
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

    override fun getLocalInterestProduct(type: Int): List<InterestProductRoomEntity> =
        interestDao.getInterestProductList(type)

    override fun addLocalInterestProduct(interestProduct: List<InterestProductRoomEntity>): Completable =
        interestDao.addInterestPurchase(interestProduct)

}