package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProductRepository {

    fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getLocalProductList(page: Int, pageSize: Int, type: Int): List<Product>

    fun addLocalProductList(list: List<Product>, type: Int): Completable

    fun getRemoteInterestProduct(type: Int): Flowable<List<Product>>

    fun getMyPurchase(): Flowable<List<Product>>

    fun getMyRent(): Flowable<List<Product>>

    fun completePurchase(postId: Int): Flowable<Unit>

    fun completeRent(postId: Int): Flowable<Unit>

    fun getRecentPurchase(): Flowable<List<Product>>

    fun getRecentRent(): Flowable<List<Product>>
}