package com.dsm.data.dataSource.rent

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.InterestDao
import com.dsm.data.local.db.dao.ProductDao
import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class RentDataSourceImpl(
    private val api: Api,
    private val productDao: ProductDao,
    private val interestDao: InterestDao
) : RentDataSource {

    override fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getRentList(page, pageSize, search, category).addSchedulers()

    override fun getLocalRentList(page: Int, pageSize: Int): List<ProductRoomEntity> =
        productDao.getProduct(page, pageSize, 1)

    override fun addLocalRentList(list: List<ProductRoomEntity>): Completable =
        productDao.addProductList(list)

    override fun getRemoteInterestRent(): Flowable<ProductListEntity> =
        api.getInterest(1).addSchedulers()

    override fun getLocalInterestRent(): List<InterestProductRoomEntity> =
        interestDao.getInterestRent()

    override fun addLocalInterestRent(interestRent: List<InterestProductRoomEntity>): Completable =
        interestDao.addInterestPurchase(interestRent)
}