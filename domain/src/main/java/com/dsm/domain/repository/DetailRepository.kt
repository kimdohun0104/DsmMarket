package com.dsm.domain.repository

import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailRepository {

    fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetail>

    fun getLocalPurchaseDetail(postId: Int): PurchaseDetail

    fun addLocalPurchaseDetail(purchaseDetail: PurchaseDetail): Completable

    fun getRemoteRentDetail(postId: Int): Flowable<RentDetail>

    fun getLocalRentDetail(postId: Int): RentDetail

    fun addLocalRentDetail(rentDetail: RentDetail): Completable

    fun interest(postId: Int, type: Int): Flowable<Unit>

    fun unInterest(postId: Int, type: Int): Flowable<Unit>

    fun getRecommendProduct(): Flowable<List<Recommend>>

    fun getRelatedProduct(postId: Int, type: Int): Flowable<List<Recommend>>

    fun modifyPurchase(param: Any): Flowable<Unit>

    fun modifyRent(param: Any): Flowable<Unit>
}