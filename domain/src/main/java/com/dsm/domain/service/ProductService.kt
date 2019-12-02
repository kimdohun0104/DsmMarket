package com.dsm.domain.service

import com.dsm.domain.entity.Product
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface ProductService {

    fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getInterest(type: Int): Flowable<Resource<List<Product>>>

    fun getMyPurchase(): Flowable<List<Product>>

    fun getMyRent(): Flowable<List<Product>>

    fun completePurchase(postId: Int): Flowable<Resource<Unit>>

    fun completeRent(postId: Int): Flowable<Resource<Unit>>

    fun getRecentPurchase(): Flowable<List<Product>>

    fun getRecentRent(): Flowable<List<Product>>
}