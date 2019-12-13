package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Recommend
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class GetRelatedUseCase(private val detailService: DetailService) : UseCase<GetRelatedUseCase.Params, List<Recommend>>() {
    override fun create(data: Params): Flowable<List<Recommend>> =
        detailService.getRelatedProduct(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}