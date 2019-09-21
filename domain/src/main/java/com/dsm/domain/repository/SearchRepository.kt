package com.dsm.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchRepository {
    fun getSearchHistory(): Flowable<List<String>>

    fun deleteSearchHistory(content: String): Completable
}