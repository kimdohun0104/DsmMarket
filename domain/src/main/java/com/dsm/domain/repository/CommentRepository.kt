package com.dsm.domain.repository

import com.dsm.domain.entity.Comment
import io.reactivex.Flowable

interface CommentRepository {

    fun postComment(params: Any): Flowable<Unit>

    fun getPurchaseComment(postId: Int): Flowable<Comment>

    fun getRentComment(postId: Int): Flowable<Comment>
}