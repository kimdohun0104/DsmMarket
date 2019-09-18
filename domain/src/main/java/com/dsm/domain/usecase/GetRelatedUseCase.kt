package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Recommend
import com.dsm.domain.repository.RecommendRepository
import io.reactivex.Flowable

class GetRelatedUseCase(private val recommendRepository: RecommendRepository) : UseCase<GetRelatedUseCase.Params, List<Recommend>>() {
    override fun create(data: Params): Flowable<List<Recommend>> =
        recommendRepository.getRelatedProduct(data.category, data.type)

    data class Params(val category: String, val type: Int)
}