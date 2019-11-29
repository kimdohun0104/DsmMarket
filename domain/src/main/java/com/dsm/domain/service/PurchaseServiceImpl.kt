package com.dsm.domain.service

import com.dsm.domain.entity.Product
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.Flowable

class PurchaseServiceImpl(private val repository: PurchaseRepository) : PurchaseService {

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        repository.getRemotePurchaseList(page, pageSize, search, category)
            .doOnNext { repository.addLocalPurchaseList(it).subscribe() }
            .onErrorReturn { repository.getLocalPurchaseList(page, pageSize) }

    override fun getInterestPurchase(): Flowable<List<Product>> =
        repository.getRemoteInterestPurchase()
            .doOnNext { repository.addLocalInterestPurchase(it).subscribe() }
            .onErrorReturn { repository.getLocalInterestPurchase() }
}