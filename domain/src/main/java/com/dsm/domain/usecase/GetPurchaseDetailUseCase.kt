package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.repository.PurchaseRepositoryBefore
import io.reactivex.Flowable

class GetPurchaseDetailUseCase(private val purchaseRepository: PurchaseRepositoryBefore) : UseCase<Int, PurchaseDetail>() {
    override fun create(data: Int): Flowable<PurchaseDetail> =
        purchaseRepository.getPurchaseDetail(data)

}