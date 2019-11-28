package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Flowable

interface RentRepository {
    fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun modifyRent(params: Any): Flowable<Unit>

    fun getRentImage(postId: Int): Flowable<String>
}