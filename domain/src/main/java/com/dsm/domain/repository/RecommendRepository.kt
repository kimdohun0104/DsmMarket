package com.dsm.domain.repository

import com.dsm.domain.entity.Recommend
import io.reactivex.Flowable

interface RecommendRepository {

    fun getRecommendProduct(category: String): Flowable<List<Recommend>>

    fun getRelatedProduct(category: String, type: Int): Flowable<List<Recommend>>
}