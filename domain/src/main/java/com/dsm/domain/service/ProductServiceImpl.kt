package com.dsm.domain.service

import com.dsm.domain.entity.Product
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.ProductRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class ProductServiceImpl(
    private val repository: ProductRepository,
    private val errorHandler: ErrorHandler
) : ProductService {

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        repository.getRemotePurchaseList(page, pageSize, search, category)
            .doOnNext { repository.addLocalProductList(it, 0).subscribe() }
            .onErrorReturn { repository.getLocalProductList(page, pageSize, 0) }

    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        repository.getRemoteRentList(page, pageSize, search, category)
            .doOnNext { repository.addLocalProductList(it, 1).subscribe() }
            .onErrorReturn { repository.getLocalProductList(page, pageSize, 1) }

    override fun getInterest(type: Int): Flowable<Resource<List<Product>>> =
        repository.getRemoteInterestProduct(type).toResource(errorHandler)

    override fun getMyPurchase(): Flowable<List<Product>> =
        repository.getMyPurchase()

    override fun getMyRent(): Flowable<List<Product>> =
        repository.getMyRent()

    override fun completePurchase(postId: Int): Flowable<Resource<Unit>> =
        repository.completePurchase(postId).toResource(errorHandler)

    override fun completeRent(postId: Int): Flowable<Resource<Unit>> =
        repository.completeRent(postId).toResource(errorHandler)

    override fun getRecentPurchase(): Flowable<List<Product>> =
        repository.getRecentPurchase()

    override fun getRecentRent(): Flowable<List<Product>> =
        repository.getRecentRent()
}