package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface RentRepository {
    fun getRentList(page: Int, pageSize: Int): Flowable<List<Product>>
}