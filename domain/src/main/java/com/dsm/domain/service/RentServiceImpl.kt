package com.dsm.domain.service

import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable

class RentServiceImpl(private val repository: RentRepository) : RentService {

    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        repository.getRemoteRentList(page, pageSize, search, category)
            .doOnNext { repository.addLocalRentList(it).subscribe() }
            .onErrorReturn { repository.getLocalRentList(page, pageSize) }

    override fun getInterestRent(): Flowable<List<Product>> =
        repository.getRemoteInterestRent()
            .doOnNext { repository.addLocalInterestRent(it).subscribe() }
            .onErrorReturn { repository.getLocalInterestRent() }
}