package com.dsm.domain.service

import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.repository.DetailRepository
import io.reactivex.Flowable

class DetailServiceImpl(private val repository: DetailRepository) : DetailService {

    override fun getPurchaseDetail(postId: Int): Flowable<PurchaseDetail> =
        repository.getRemotePurchaseDetail(postId)
            .doOnNext { repository.addLocalPurchaseDetail(it).subscribe() }
            .onErrorReturn { repository.getLocalPurchaseDetail(postId) }

}