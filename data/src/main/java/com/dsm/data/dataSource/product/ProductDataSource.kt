package com.dsm.data.dataSource.product

import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProductDataSource {

    fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getLocalProductList(page: Int, pageSize: Int, type: Int): List<ProductRoomEntity>

    fun addLocalProductList(list: List<ProductRoomEntity>) : Completable

    fun getRemoteInterestProduct(type: Int): Flowable<ProductListEntity>

    fun getLocalInterestProduct(type: Int): List<InterestProductRoomEntity>

    fun addLocalInterestProduct(interestProduct: List<InterestProductRoomEntity>): Completable
}