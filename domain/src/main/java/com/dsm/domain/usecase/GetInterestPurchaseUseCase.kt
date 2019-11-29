package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.service.PurchaseService
import io.reactivex.Flowable

class GetInterestPurchaseUseCase(private val purchaseService: PurchaseService) : UseCase<Unit, List<Product>>() {
    override fun create(data: Unit): Flowable<List<Product>> =
        purchaseService.getInterestPurchase()

}