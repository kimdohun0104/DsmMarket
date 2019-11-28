package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface PurchaseRepositoryBefore {

    fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun modifyPurchase(params: Any): Flowable<Unit>

    fun getPurchaseImage(postId: Int): Flowable<List<String>>
}