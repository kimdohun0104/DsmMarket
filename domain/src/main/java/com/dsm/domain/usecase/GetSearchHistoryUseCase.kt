package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.SearchHistory
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Flowable

class GetSearchHistoryUseCase(private val searchRepository: SearchRepository) : UseCase<Unit, List<SearchHistory>>() {
    override fun create(data: Unit): Flowable<List<SearchHistory>> =
        searchRepository.getSearchHistory()

}