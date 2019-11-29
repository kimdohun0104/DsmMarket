package com.dsm.data.dataSource.rent

import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.RentImageEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

interface RentDataSourceBefore {
    fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity>

    fun modifyRent(params: Any): Flowable<Response<Unit>>

    fun addSearchHistory(search: String): Completable

    fun getRentImage(postId: Int): Flowable<RentImageEntity>
}