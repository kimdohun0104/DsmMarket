package com.dsm.domain.repository

import com.dsm.domain.entity.Comment
import io.reactivex.Completable
import io.reactivex.Flowable

interface CommentRepository {

    fun postComment(params: Any): Flowable<Unit>

    fun getRemoteCommentList(postId: Int, type: Int): Flowable<List<Comment>>

    fun getLocalCommentList(postId: Int, type: Int): List<Comment>?

    fun addLocalComment(comment: List<Comment>, postId: Int, type: Int): Completable
}