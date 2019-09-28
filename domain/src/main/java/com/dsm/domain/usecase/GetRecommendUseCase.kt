package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Recommend
import com.dsm.domain.repository.RecommendRepository
import io.reactivex.Flowable

class GetRecommendUseCase(private val recommendRepository: RecommendRepository) : UseCase<Int, List<Recommend>>() {
    override fun create(data: Int): Flowable<List<Recommend>> =
        recommendRepository.getRecommendProduct(data)
}