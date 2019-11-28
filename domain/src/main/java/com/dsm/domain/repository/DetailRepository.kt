package com.dsm.domain.repository

import com.dsm.domain.entity.PurchaseDetail
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailRepository {

    fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetail>

    fun getLocalPurchaseDetail(postId: Int): PurchaseDetail

    fun addLocalPurchaseDetail(purchaseDetail: PurchaseDetail): Completable
}