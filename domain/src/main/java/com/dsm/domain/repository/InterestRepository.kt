package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface InterestRepository {

    fun getInterest(type: Int): Flowable<List<Product>>
}