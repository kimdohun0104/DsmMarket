package com.dsm.domain.repository

import io.reactivex.Flowable

interface InterestRepository {
    fun interest(postId: Int, type: Int): Flowable<Unit>

    fun unInterest(postId: Int, type: Int): Flowable<Unit>
}