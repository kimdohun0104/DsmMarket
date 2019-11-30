package com.dsm.domain.service

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface ProductService {

    fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getInterest(type: Int): Flowable<List<Product>>
}