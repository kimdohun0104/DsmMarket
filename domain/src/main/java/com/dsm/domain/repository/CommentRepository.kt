package com.dsm.domain.repository

import com.dsm.domain.entity.Comment
import io.reactivex.Flowable

interface CommentRepository {

    fun postComment(params: Any): Flowable<Unit>

    fun getCommentList(postId: Int, type: Int): Flowable<List<Comment>>
}