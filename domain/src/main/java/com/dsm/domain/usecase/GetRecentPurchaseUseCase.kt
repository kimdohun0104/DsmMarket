package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RecentRepository
import io.reactivex.Flowable

class GetRecentPurchaseUseCase(private val recentRepository: RecentRepository) : UseCase<Unit, List<Product>>() {
    override fun create(data: Unit): Flowable<List<Product>> =
        recentRepository.getRecentPurchase()

}