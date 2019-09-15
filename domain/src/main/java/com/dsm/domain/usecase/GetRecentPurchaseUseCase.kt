package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Recent
import com.dsm.domain.repository.RecentRepository
import io.reactivex.Flowable

class GetRecentPurchaseUseCase(private val recentRepository: RecentRepository) : UseCase<Unit, List<Recent>>() {
    override fun create(data: Unit): Flowable<List<Recent>> =
        recentRepository.getRecentPurchase()

}