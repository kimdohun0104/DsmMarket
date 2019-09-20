package com.dsm.data.repository

import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource) : SearchRepository {

    private val productMapper = ProductMapper()

    override fun getSearchHistory(): Flowable<List<String>> =
        searchDataSource.getSearchHistory().map { it.map { it.content } }

    override fun deleteSearchHistory(content: String): Completable =
        searchDataSource.deleteSearchHistory(content)

    override fun searchPurchase(page: Int, pageSize: Int, search: String): Flowable<List<Product>> =
        searchDataSource.searchPurchase(page, pageSize, search).map(productMapper::mapFrom)
            .doOnNext { searchDataSource.addSearchHistory(SearchHistoryRoomEntity(search)).subscribe() }

    override fun searchRent(page: Int, pageSize: Int, search: String): Flowable<List<Product>> =
        searchDataSource.searchRent(page, pageSize, search).map(productMapper::mapFrom)
            .doOnNext { searchDataSource.addSearchHistory(SearchHistoryRoomEntity(search)).subscribe() }
}