package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class ModifyPurchaseUseCase(private val detailService: DetailService) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        detailService.modifyPurchase(data)

}