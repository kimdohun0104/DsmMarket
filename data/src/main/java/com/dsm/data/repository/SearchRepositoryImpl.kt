package com.dsm.data.repository

import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource) : SearchRepository {

    override fun getSearchHistory(): Flowable<List<String>> =
        searchDataSource.getSearchHistory().map { it.map { it.content } }

    override fun deleteSearchHistory(content: String): Completable =
        searchDataSource.deleteSearchHistory(content)

    override fun addSearchHistory(search: String): Completable =
        searchDataSource.addSearchHistory(SearchHistoryRoomEntity(search))
}