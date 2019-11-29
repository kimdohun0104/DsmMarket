package com.dsm.data.dataSource.rent

import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface RentDataSource {

    fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getLocalRentList(page: Int, pageSize: Int): List<ProductRoomEntity>

    fun addLocalRentList(list: List<ProductRoomEntity>): Completable

    fun getRemoteInterestRent(): Flowable<ProductListEntity>

    fun getLocalInterestRent(): List<InterestProductRoomEntity>

    fun addLocalInterestRent(interestRent: List<InterestProductRoomEntity>): Completable
}