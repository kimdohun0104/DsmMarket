package com.dsm.domain.service

import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface DetailService {

    fun getPurchaseDetail(postId: Int): Flowable<Resource<PurchaseDetail>>

    fun getRentDetail(postId: Int): Flowable<Resource<RentDetail>>

    fun interest(postId: Int, type: Int): Flowable<Resource<Unit>>

    fun unInterest(postId: Int, type: Int): Flowable<Resource<Unit>>

    fun getRecommendProduct(): Flowable<Resource<List<Recommend>>>

    fun getRelatedProduct(postId: Int, type: Int): Flowable<Resource<List<Recommend>>>
}