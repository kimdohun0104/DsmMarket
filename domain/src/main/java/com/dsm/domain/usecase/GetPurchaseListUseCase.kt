package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Purchase
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.Flowable

class GetPurchaseListUseCase(private val purchaseRepository: PurchaseRepository) : UseCase<GetPurchaseListUseCase.Params, List<Purchase>>() {

    override fun create(data: Params): Flowable<List<Purchase>> =
        purchaseRepository.getPurchaseList(data.page, data.pageSize)

    data class Params(val page: Int, val pageSize: Int)
}