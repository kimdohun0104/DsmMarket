package com.dsm.domain.usecase

import com.dsm.domain.base.CompletableUseCase
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Completable

class DeleteSearchHistoryUseCase(private val searchRepository: SearchRepository) : CompletableUseCase<String>() {
    override fun create(data: String): Completable =
        searchRepository.deleteSearchHistory(data)

}