package com.dsm.domain.repository

import io.reactivex.Flowable

interface PurchaseRepositoryBefore {

    fun modifyPurchase(params: Any): Flowable<Unit>

    fun getPurchaseImage(postId: Int): Flowable<List<String>>
}