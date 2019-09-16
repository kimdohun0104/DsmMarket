package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import com.dsm.domain.entity.SearchHistory
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchRepository {
    fun addSearchHistory(searchHistory: SearchHistory): Completable

    fun getSearchHistory(): Flowable<List<SearchHistory>>

    fun deleteSearchHistory(content: String): Completable

    fun searchPurchase(page: Int, pageSize: Int, search: String): Flowable<List<Product>>

    fun searchRent(page: Int, pageSize: Int, search: String): Flowable<List<Product>>
}