package com.dsm.domain.service

import com.dsm.domain.entity.Comment
import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface CommentService {

    fun getCommentList(postId: Int, type: Int): Flowable<Success<List<Comment>>>

    fun postComment(param: Any): Flowable<Unit>
}