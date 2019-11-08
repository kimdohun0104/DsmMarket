package com.dsm.domain.repository

import com.dsm.domain.entity.Recommend
import io.reactivex.Flowable

interface RecommendRepository {

    fun getRecommendProduct(): Flowable<List<Recommend>>

    fun getRelatedProduct(postId: Int, type: Int): Flowable<List<Recommend>>
}