package com.dsm.data.dataSource.search

import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchDataSource {
    fun addSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable

    fun getSearchHistory(): Flowable<List<SearchHistoryRoomEntity>>

    fun deleteSearchHistory(content: String): Completable

    fun searchPurchase(page: Int, pageSize: Int, search: String): Flowable<ProductListEntity>

    fun searchRent(page: Int, pageSize: Int, search: String): Flowable<ProductListEntity>
}