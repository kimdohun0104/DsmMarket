package com.dsm.domain.service

import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface DetailService {

    fun getPurchaseDetail(postId: Int): Flowable<Success<PurchaseDetail>>

    fun getRentDetail(postId: Int): Flowable<Success<RentDetail>>

    fun interest(postId: Int, type: Int): Flowable<Unit>

    fun unInterest(postId: Int, type: Int): Flowable<Unit>

    fun getRecommendProduct(): Flowable<List<Recommend>>

    fun getRelatedProduct(postId: Int, type: Int): Flowable<List<Recommend>>

    fun modifyPurchase(param: Any): Flowable<Unit>

    fun modifyRent(param: Any): Flowable<Unit>
}