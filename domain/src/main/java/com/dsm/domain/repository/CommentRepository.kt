package com.dsm.domain.repository

import io.reactivex.Flowable

interface CommentRepository {

    fun postComment(params: Any): Flowable<Unit>
}