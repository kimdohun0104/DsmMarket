package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class UnInterestUseCase(private val detailService: DetailService) : UseCase<UnInterestUseCase.Params, Unit>() {
    override fun create(data: Params): Flowable<Unit> =
        detailService.unInterest(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}