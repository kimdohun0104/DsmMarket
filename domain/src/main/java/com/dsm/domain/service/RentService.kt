package com.dsm.domain.service

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface RentService {

    fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getInterestRent(): Flowable<List<Product>>
}