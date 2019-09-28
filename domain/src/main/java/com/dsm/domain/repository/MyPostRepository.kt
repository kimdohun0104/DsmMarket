package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface MyPostRepository {
    fun getMyPurchase(): Flowable<List<Product>>

    fun getMyRent(): Flowable<List<Product>>

    fun completePurchase(postId: Int): Flowable<Unit>

    fun completeRent(postId: Int): Flowable<Unit>
}