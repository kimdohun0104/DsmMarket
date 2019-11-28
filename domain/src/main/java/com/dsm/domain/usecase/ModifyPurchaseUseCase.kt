package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PurchaseRepositoryBefore
import io.reactivex.Flowable

class ModifyPurchaseUseCase(private val purchaseRepository: PurchaseRepositoryBefore) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        purchaseRepository.modifyPurchase(data)

}