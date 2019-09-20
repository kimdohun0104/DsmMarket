package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchRepository {
    fun getSearchHistory(): Flowable<List<String>>

    fun deleteSearchHistory(content: String): Completable

    fun searchPurchase(page: Int, pageSize: Int, search: String): Flowable<List<Product>>

    fun searchRent(page: Int, pageSize: Int, search: String): Flowable<List<Product>>
}