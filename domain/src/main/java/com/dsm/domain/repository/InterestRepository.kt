package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface InterestRepository {
    fun interest(postId: Int, type: Int): Flowable<Unit>

    fun unInterest(postId: Int, type: Int): Flowable<Unit>

    fun getInterest(type: Int): Flowable<List<Product>>
}