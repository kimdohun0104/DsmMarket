package com.dsm.domain.service

import com.dsm.domain.entity.Product
import com.dsm.domain.repository.ProductRepository
import io.reactivex.Flowable

class ProductServiceImpl(private val repository: ProductRepository) : ProductService {

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        repository.getRemotePurchaseList(page, pageSize, search, category)
            .doOnNext { repository.addLocalProductList(it, 0).subscribe() }
            .onErrorReturn { repository.getLocalProductList(page, pageSize, 0) }

    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        repository.getRemoteRentList(page, pageSize, search, category)
            .doOnNext { repository.addLocalProductList(it, 1).subscribe() }
            .onErrorReturn { repository.getLocalProductList(page, pageSize, 1) }

    override fun getInterest(type: Int): Flowable<List<Product>> =
        repository.getRemoteInterestProduct(type)
            .doOnNext { repository.addLocalInterestProduct(it, type).subscribe() }
            .onErrorReturn { repository.getLocalInterestProduct(type) }

}