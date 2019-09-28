package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.Flowable

class GetPurchaseImageUseCase(private val purchaseRepository: PurchaseRepository) : UseCase<Int, List<String>>() {
    override fun create(data: Int): Flowable<List<String>> =
        purchaseRepository.getPurchaseImage(data)

}