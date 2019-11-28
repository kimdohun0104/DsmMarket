package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PurchaseRepositoryBefore
import io.reactivex.Flowable

class GetPurchaseImageUseCase(private val purchaseRepository: PurchaseRepositoryBefore) : UseCase<Int, List<String>>() {
    override fun create(data: Int): Flowable<List<String>> =
        purchaseRepository.getPurchaseImage(data)

}