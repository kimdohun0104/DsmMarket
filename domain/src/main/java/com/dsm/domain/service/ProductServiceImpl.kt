package com.dsm.domain.service

import com.dsm.domain.entity.Product
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.ProductRepository
import io.reactivex.Flowable

class ProductServiceImpl(
    private val repository: ProductRepository,
    private val errorHandler: ErrorHandler
) : ProductService {

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<Success<List<Product>>> =
        repository.getRemotePurchaseList(page, pageSize, search, category)
            .doOnNext { repository.addLocalProductList(it, 0).subscribe() }
            .toSuccess(errorHandler)

    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<Success<List<Product>>> =
        repository.getRemoteRentList(page, pageSize, search, category)
            .doOnNext { repository.addLocalProductList(it, 1).subscribe() }
            .toSuccess(errorHandler)

    override fun getInterest(type: Int): Flowable<List<Product>> =
        repository.getRemoteInterestProduct(type).handleError(errorHandler)

    override fun getMyPurchase(): Flowable<List<Product>> =
        repository.getMyPurchase().handleError(errorHandler)

    override fun getMyRent(): Flowable<List<Product>> =
        repository.getMyRent().handleError(errorHandler)

    override fun completePurchase(postId: Int): Flowable<Unit> =
        repository.completePurchase(postId).handleError(errorHandler)

    override fun completeRent(postId: Int): Flowable<Unit> =
        repository.completeRent(postId).handleError(errorHandler)

    override fun getRecentPurchase(): Flowable<List<Product>> =
        repository.getRecentPurchase().handleError(errorHandler)

    override fun getRecentRent(): Flowable<List<Product>> =
        repository.getRecentRent().handleError(errorHandler)
}