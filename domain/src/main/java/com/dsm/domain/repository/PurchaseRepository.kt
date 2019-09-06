package com.dsm.domain.repository

import com.dsm.domain.entity.Purchase
import io.reactivex.Flowable

interface PurchaseRepository {

    fun getPurchaseList(page: Int, pageSize: Int): Flowable<List<Purchase>>
}