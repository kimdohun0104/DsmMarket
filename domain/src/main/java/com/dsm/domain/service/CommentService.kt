package com.dsm.domain.service

import com.dsm.domain.entity.Comment
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface CommentService {

    fun getCommentList(postId: Int, type: Int): Flowable<List<Comment>>

    fun postComment(param: Any): Flowable<Resource<Unit>>
}