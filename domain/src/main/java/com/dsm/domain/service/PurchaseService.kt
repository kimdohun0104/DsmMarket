package com.dsm.domain.service

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface PurchaseService {

    fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>
}