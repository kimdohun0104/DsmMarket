package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Flowable

interface PurchaseRepository {

    fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getLocalPurchaseList(page: Int, pageSize: Int): List<Product>

    fun addLocalPurchaseList(list: List<Product>): Completable
}