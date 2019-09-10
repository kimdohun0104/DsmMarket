package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import com.dsm.domain.entity.RentDetail
import io.reactivex.Flowable

interface RentRepository {
    fun getRentList(page: Int, pageSize: Int): Flowable<List<Product>>

    fun getRentDetail(postId: Int): Flowable<RentDetail>
}