package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.error.Success
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class GetPurchaseDetailUseCase(private val detailService: DetailService) : UseCase<Int, Success<PurchaseDetail>>() {
    override fun create(data: Int): Flowable<Success<PurchaseDetail>> =
        detailService.getPurchaseDetail(data)

}