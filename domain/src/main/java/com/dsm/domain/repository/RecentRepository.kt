package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface RecentRepository {

    fun getRecentPurchase(): Flowable<List<Product>>

    fun getRecentRent(): Flowable<List<Product>>
}