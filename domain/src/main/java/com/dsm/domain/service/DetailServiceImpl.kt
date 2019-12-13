package com.dsm.domain.service

import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.DetailRepository
import io.reactivex.Flowable

class DetailServiceImpl(
    private val repository: DetailRepository,
    private val errorHandler: ErrorHandler
) : DetailService {

    override fun getPurchaseDetail(postId: Int): Flowable<Success<PurchaseDetail>> =
        repository.getRemotePurchaseDetail(postId)
            .doOnNext { repository.addLocalPurchaseDetail(it).subscribe() }
            .toSuccess(errorHandler)
            .onErrorReturn {
                repository.getLocalPurchaseDetail(postId)?.let {detail ->
                    return@onErrorReturn Success(detail, true)
                }
            }

    override fun getRentDetail(postId: Int): Flowable<Success<RentDetail>> =
        repository.getRemoteRentDetail(postId)
            .doOnNext { repository.addLocalRentDetail(it).subscribe() }
            .toSuccess(errorHandler)
            .onErrorReturn {
                repository.getLocalRentDetail(postId)?.let { detail ->
                    return@onErrorReturn Success(detail, true)
                }
            }

    override fun interest(postId: Int, type: Int): Flowable<Unit> =
        repository.interest(postId, type).handleError(errorHandler)

    override fun unInterest(postId: Int, type: Int): Flowable<Unit> =
        repository.unInterest(postId, type).handleError(errorHandler)

    override fun getRecommendProduct(): Flowable<List<Recommend>> =
        repository.getRecommendProduct().handleError(errorHandler)

    override fun getRelatedProduct(postId: Int, type: Int): Flowable<List<Recommend>> =
        repository.getRelatedProduct(postId, type).handleError(errorHandler)

    override fun modifyPurchase(param: Any): Flowable<Unit> =
        repository.modifyPurchase(param).handleError(errorHandler)

    override fun modifyRent(param: Any): Flowable<Unit> =
        repository.modifyRent(param).handleError(errorHandler)
}