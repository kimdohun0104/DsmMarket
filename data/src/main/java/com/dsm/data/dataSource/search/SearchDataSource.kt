package com.dsm.data.dataSource.search

import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchDataSource {
    fun addSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable

    fun getSearchHistory(): Flowable<List<SearchHistoryRoomEntity>>

    fun deleteSearchHistory(content: String): Completable
}