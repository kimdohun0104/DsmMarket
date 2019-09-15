package com.dsm.data.repository

import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.mapper.SearchHistoryMapper
import com.dsm.domain.entity.SearchHistory
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource) : SearchRepository {

    private val searchHistoryMapper = SearchHistoryMapper()

    override fun addSearchHistory(searchHistory: SearchHistory): Completable =
        searchDataSource.addSearchHistory(searchHistoryMapper.mapFrom(searchHistory))

    override fun getSearchHistory(): Flowable<List<SearchHistory>> =
        searchDataSource.getSearchHistory().map(searchHistoryMapper::mapFrom)

    override fun deleteSearchHistory(content: String): Completable =
        searchDataSource.deleteSearchHistory(content)
}