package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.error.Resource
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class InterestUseCase(private val detailService: DetailService) : UseCase<InterestUseCase.Params, Resource<Unit>>() {
    override fun create(data: Params): Flowable<Resource<Unit>> =
        detailService.interest(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}