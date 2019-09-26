package com.dsm.data.dataSource.rent

import com.dsm.data.local.db.entity.RentDetailRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.RentDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

interface RentDataSource {
    fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun getRemoteRentDetail(postId: Int): Flowable<Response<RentDetailEntity>>

    fun getLocalRentDetail(postId: Int): RentDetailRoomEntity

    fun addLocalRentDetail(rentDetailRoomEntity: RentDetailRoomEntity): Completable

    fun modifyRent(params: Any): Flowable<Response<Unit>>

    fun addSearchHistory(search: String): Completable
}