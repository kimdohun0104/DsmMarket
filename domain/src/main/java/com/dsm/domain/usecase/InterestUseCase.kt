package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.InterestRepository
import io.reactivex.Flowable

class InterestUseCase(private val interestRepository: InterestRepository) : UseCase<InterestUseCase.Params, Unit>() {
    override fun create(data: Params): Flowable<Unit> =
        interestRepository.interest(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}