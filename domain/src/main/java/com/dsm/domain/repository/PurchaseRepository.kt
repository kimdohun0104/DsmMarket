package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import com.dsm.domain.entity.PurchaseDetail
import io.reactivex.Flowable

interface PurchaseRepository {

    fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getPurchaseDetail(postId: Int): Flowable<PurchaseDetail>

    fun modifyPurchase(params: Any): Flowable<Unit>
}