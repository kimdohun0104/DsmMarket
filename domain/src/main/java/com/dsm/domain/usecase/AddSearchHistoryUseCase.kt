package com.dsm.domain.usecase

import com.dsm.domain.base.CompletableUseCase
import com.dsm.domain.entity.SearchHistory
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Completable

class AddSearchHistoryUseCase(private val searchRepository: SearchRepository) : CompletableUseCase<SearchHistory>() {
    override fun create(data: SearchHistory): Completable =
        searchRepository.addSearchHistory(data)
}