package com.dsm.domain.service

import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.DetailRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class DetailServiceImpl(
    private val repository: DetailRepository,
    private val errorHandler: ErrorHandler
) : DetailService {

    override fun getPurchaseDetail(postId: Int): Flowable<Resource<PurchaseDetail>> =
        repository.getRemotePurchaseDetail(postId)
            .doOnNext { repository.addLocalPurchaseDetail(it).subscribe() }
            .onErrorReturn { repository.getLocalPurchaseDetail(postId) }
            .toResource(errorHandler)

    override fun getRentDetail(postId: Int): Flowable<Resource<RentDetail>> =
        repository.getRemoteRentDetail(postId)
            .doOnNext { repository.addLocalRentDetail(it).subscribe() }
            .onErrorReturn { repository.getLocalRentDetail(postId) }
            .toResource(errorHandler)

    override fun interest(postId: Int, type: Int): Flowable<Resource<Unit>> =
        repository.interest(postId, type).toResource(errorHandler)

    override fun unInterest(postId: Int, type: Int): Flowable<Resource<Unit>> =
        repository.unInterest(postId, type).toResource(errorHandler)

    override fun getRecommendProduct(): Flowable<Resource<List<Recommend>>> =
        repository.getRecommendProduct().toResource(errorHandler)

    override fun getRelatedProduct(postId: Int, type: Int): Flowable<Resource<List<Recommend>>> =
        repository.getRelatedProduct(postId, type).toResource(errorHandler)

    override fun modifyPurchase(param: Any): Flowable<Resource<Unit>> =
        repository.modifyPurchase(param).toResource(errorHandler)

    override fun modifyRent(param: Any): Flowable<Resource<Unit>> =
        repository.modifyRent(param).toResource(errorHandler)
}